package controllers;

import models.DataManagers.CropMetaData;
import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Entity.Player.PlayerInventory;
import models.GameWorld.Enums.Direction;
import models.GameWorld.Farming.Crop;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Map.ForestMap;
import models.GameWorld.Map.StandardMap;
import models.Menu.CheatCommands;
import models.Menu.Command;
import models.Menu.GameMenuCommands;
import models.Result;
import utils.PathUtils;
import views.GameMenu;
import views.MapPrinter;

import java.util.List;

public class GameMenuController {
    private final CheatController cheatController;
    private Game game;

    public GameMenuController() {
        this.cheatController = new CheatController();
        this.game = null;
    }

    public void setGame(Game game) {
        this.game = game;
        cheatController.setGame(game);
    }

    public Result processCommand(String command) {
        if (game == null) return new Result(false, "Game is null!");

        CheatCommands matchedCheatCommand = Command.findCommand(command, CheatCommands.values());
        if (matchedCheatCommand != null) return cheatController.processCheat(command);

        GameMenuCommands matchedGameCommand = Command.findCommand(command, GameMenuCommands.values());
        return switch (matchedGameCommand) {
            case null -> Result.invalidCommand;
            case WhichMap -> whichMap();
            case NextTurn -> nextTurn();
            case ShowTime ->
                    new Result(true, game.getTimeState().getFormattedTime());
            case ShowDate ->
                    new Result(true, game.getTimeState().getFormattedDate(game.getCurrentSeason()));
            case ShowDateTime ->
                    new Result(true, game.getTimeState().getFormattedDateTime(game.getCurrentSeason()));
            case ShowDay ->
                    new Result(true, game.getTimeState().getDayOfTheWeek().toString());
            case ShowSeason ->
                    new Result(true, game.getCurrentSeason().toString());
            case ShowWeather ->
                    new Result(true, game.getWeather().getCurrentWeather().toString());
            case ShowWeatherForecast ->
                    new Result(true, game.getWeather().getNextDayWeather().toString());
            case Walk -> processWalking(command);
            case PrintMap -> processMapPrinting(command);
            case MapHelp -> {
                MapPrinter.help();
                yield new Result(true, "");
            }
            case ShowEnergy ->
                    new Result(true,
                            String.format(
                                    "Energy: %d/%d",
                                    game.getCurrentPlayer().getEnergy(),
                                    game.getCurrentPlayer().getMaxEnergy()
                            )
                    );
            case ShowBalance ->
                    new Result(true, String.format("Balance: %d$", game.getCurrentPlayer().getMoney()));
            case ShowInventory -> {
                GameMenu.showPlayerInventory(game.getCurrentPlayer());
                yield new Result(true, "");
            }
            case InventoryTrash -> processInventoryTrash(command);
            case EquipTool -> processEquipTool(command);
            case ShowCurrentTool ->
                    new Result(true, game.getCurrentPlayer().getInventory().getCurrentTool().getName());
            case ShowAvailableTools -> {
                GameMenu.showPlayerTools(game.getCurrentPlayer());
                yield new Result(true, "");
            }
            case UseTool -> useTool(command);
            case ShowCraftInfo -> processCraftInfo(command);
            case ShowAllCrops -> {
                GameMenu.showAllCrops();
                yield new Result(true, "");
            }
            case ShowAllTrees -> {
                GameMenu.showAllTrees();
                yield new Result(true, "");
            }
            default -> new Result(false, "Coming Soon...");
        };
    }

    public Result whichMap() {
        if (game.getCurrentPlayer().getFarm() instanceof StandardMap) {
            return new Result(true, "You are on the Standard Map.");
        } else if (game.getCurrentPlayer().getFarm() instanceof ForestMap) {
            return new Result(true, "You are on the Forest Map.");
        } else {
            return new Result(false, "You are on an unknown map!");
        }
    }

    private Result nextTurn() {
        if (game.areAllPlayersFainted()) {
            game.getTimeState().updateDate(1);
            return new Result(false, "All Players Fainted!");
        }

        do {
            if (game.isItLastTurn()) game.getTimeState().updateTime(1);
            game.nextTurn();
        } while (game.getCurrentPlayer().isFainted());

        return new Result(true, "It's \"" + game.getCurrentPlayer().getName() + "\" turn now.");
    }

    private Result processMapPrinting(String command) {
        int size = Integer.parseInt(command.split("\\s+-s\\s+")[1]);
        MapPrinter.printFarm(game.getCurrentPlayer(), size);
        return new Result(true, "");
    }

    private Result processWalking(String command) {
        String[] parts = command.split("\\s+-y\\s+|\\s+-x\\s+");
        int y = Integer.parseInt(parts[1]);
        int x = Integer.parseInt(parts[2]);
        return walkTo(new Coordinate(y, x));
    }

    private Result walkTo(Coordinate dest) {
        Player player = game.getCurrentPlayer();

        if (!player.getFarm().isCoordinateWithinMap(dest)) {
            return new Result(false, "Destination out of bounds!");
        }
        if (!player.getFarm().getTile(dest).isWalkable()) {
            return new Result(false, "The destination isn't walkable!");
        }

        List<Coordinate> path = PathUtils.findPathAStar(player.getFarm(), player.getCoordinate(), dest);
        if (path == null || path.size() < 2) {
            return new Result(false, "No path found.");
        }

        List<Direction> dirs = PathUtils.calculateDirections(path);
        int turns = PathUtils.countTurns(dirs);
        int tiles = path.size() - 1;

        int energyNeeded = (tiles + 10 * turns) / 20;
        if (!player.isEnergyUnlimited()) {
            if (player.getEnergy() < energyNeeded) {
                // Calculate how far the player can go with current energy
                int maxTiles = (player.getEnergy() * 20 - turns * 10);
                if (maxTiles <= 0) {
                    player.setFainted(true);
                    return new Result(
                            false,
                            "Not enough energy to move even one tile. Player fainted!"
                    );
                }

                // Move as far as possible along the path
                int reachableTiles = Math.min(maxTiles, path.size() - 1);
                Coordinate partialDest = path.get(reachableTiles);
                player.setCoordinate(partialDest);
                player.setEnergy(0);

                return new Result(
                        true,
                        "Moved " + reachableTiles + " tiles before fainting at " + partialDest +
                                " (Used all " + player.getEnergy() + " energy)"
                );
            }
            player.changeEnergy(-energyNeeded);
        }

        player.setCoordinate(dest);
        return new Result(
                true,
                "Moved to " + dest +
                        " (Tiles: " + tiles + ", Turns: " + turns + ", Energy used: " + energyNeeded + ")"
        );
    }

    private Result processInventoryTrash(String command) {
        String itemName;
        int number;
        try {
            if (command.contains("-n")) {
                String[] parts = command.split("\\s+-i\\s+|\\s+-n\\s+");
                itemName = parts[1];
                number = Integer.parseInt(parts[2]);
            } else {
                itemName = command.split("\\s+-i\\s+")[1];
                number = -1;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return Result.invalidCommand;
        } catch (NumberFormatException e) {
            return new Result(false, "Please enter a number!");
        }

        Item item = game.getCurrentPlayer().getInventory().findItem(itemName);
        if (item == null)
            return new Result(false, "Item not found! Item's name must be entered in PascalCase.");

        return trashItem(item, number);
    }

    private Result trashItem(Item item, int quantity) {
        if (!item.isStackable())
            return new Result(false, "You can't remove a non-stackable item!");

        PlayerInventory inventory = game.getCurrentPlayer().getInventory();
        double refundPercentage = inventory.getTrashCan().getRefundPercentage();

        if (quantity < 0 || quantity >= inventory.getMainInventory().getItemQuantity(item)) {
            int amount = inventory.getMainInventory().removeItem(item);
            game.getCurrentPlayer().changeMoney((int) (item.getPrice() * amount * refundPercentage));
            return new Result(true, "Item removed successfully!");
        } else {
            int amount = inventory.getMainInventory().reduceItemQuantity(item, quantity);
            game.getCurrentPlayer().changeMoney((int) (item.getPrice() * amount * refundPercentage));
            return new Result(true, "Item's quantity reduced successfully!");
        }
    }

    private Result processEquipTool(String command) {
        String toolName = command.split("\\s+")[2];

        Tool tool = (Tool) game.getCurrentPlayer().getInventory().findItem(toolName);
        if (tool == null) return new Result(false, "Tool not found!");

        game.getCurrentPlayer().getInventory().setCurrentTool(tool);
        return new Result(true, "Current tool set to " + toolName);
    }

    private Result useTool(String command) {
        String dir = command.split("\\s+-d\\s+")[1];
        Direction direction = Direction.valueOf(dir.toUpperCase());
        if (direction == null) return new Result(false, "Invalid direction!");

        Player player = game.getCurrentPlayer();
        int oldEnergy = player.getEnergy();

        Tool tool = player.getInventory().getCurrentTool();
        tool.use(
                new Coordinate(
                player.getCoordinate().y() + direction.dy,
                player.getCoordinate().x() + direction.dx
                ),
                player,
                game
        );

        return new Result(true, "Energy Consumed: " + (oldEnergy - player.getEnergy()));
    }

    private Result processCraftInfo(String command) {
        String craftName = command.split("\\s+-n\\s+")[1];
        Crop crop = CropMetaData.getCrop(craftName);
        if (crop == null) return new Result(false, "Crop not found!");
        return new Result(true, crop.toString());
    }
}

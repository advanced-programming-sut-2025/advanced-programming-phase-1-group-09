package controllers;

import models.DataManagers.CropMetaData;
import models.DataManagers.ItemHolder;
import models.DataManagers.TreeMetaData;
import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Entity.Player.PlayerInventory;
import models.GameWorld.Enums.Direction;
import models.GameWorld.Farming.*;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Tools.PrimaryTools.WateringCan;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Map.Elements.MapElement;
import models.GameWorld.Map.ForestMap;
import models.GameWorld.Map.StandardMap;
import models.GameWorld.Map.TerrainType;
import models.GameWorld.Map.Tile;
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
            case GoPublic -> goPublic();
            case GoHome -> goHome();
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
            case Plant -> processPlanting(command);
            case ShowPlant -> processShowingPlant(command);
            case ShowCurrentWater -> showWateringCan();
            default -> new Result(false, "Coming Soon...");
        };
    }

    private Result whichMap() {
        if (game.getCurrentPlayer().getField() instanceof StandardMap) {
            return new Result(true, "You are on the Standard Map.");
        } else if (game.getCurrentPlayer().getField() instanceof ForestMap) {
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

        if (!player.getField().isCoordinateWithinMap(dest)) {
            return new Result(false, "Destination out of bounds!");
        }
        if (!player.getField().getTile(dest).isWalkable()) {
            return new Result(false, "The destination isn't walkable!");
        }

        List<Coordinate> path = PathUtils.findPath(player.getField(), player.getCoordinate(), dest);
        if (path == null || path.size() < 2) {
            return new Result(false, "No path found.");
        }

        List<Direction> dirs = PathUtils.calculateDirections(path);
        int turns = PathUtils.countTurns(dirs);
        int tiles = path.size() - 1;

        int energyNeeded = PathUtils.calculateEnergy(path);
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
        player.collectAround();
        return new Result(
                true,
                "Moved to " + dest +
                        " (Tiles: " + tiles + ", Turns: " + turns + ", Energy used: " + energyNeeded + ")"
        );
    }

    private Result goPublic() {
        Player player = game.getCurrentPlayer();
        if (!player.isHome()) return new Result(false, "You are already in public!");

        player.setIsHome(false);
        player.setCoordinate(player.getPublicMap().getStartingPoint());
        return new Result(true, "You are now in public.");
    }

    private Result goHome() {
        Player player = game.getCurrentPlayer();
        if (player.isHome()) return new Result(false, "You are already home!");

        player.setIsHome(true);
        player.setCoordinate(player.getFarm().getStartingPoint());
        return new Result(true, "You are now home.");
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
        Direction direction;
        try {
            direction = Direction.valueOf(dir.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new Result(false, "Invalid direction!");
        }

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
        CropDefinition cropDefinition = CropMetaData.getCrop(craftName);
        if (cropDefinition == null) return new Result(false, "Crop not found!");
        return new Result(true, cropDefinition.toString());
    }

    private Result processPlanting(String command) {
        String[] parts = command.split("\\s+-s\\s+|\\s+-d\\s+");
        Seed seed = ItemHolder.getSeed(parts[1]);
        if (seed == null) return new Result(false, "There is no seed with that name!");

        Direction direction;
        try {
            direction = Direction.valueOf(parts[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            return new Result(false, "Invalid direction!");
        }

        return plant(seed, direction);
    }

    private Result plant(Seed seed, Direction direction) {
        Player player = game.getCurrentPlayer();
        Coordinate target = new Coordinate(
                player.getCoordinate().y() + direction.dy,
                player.getCoordinate().x() + direction.dx
        );

        Tile targetTile = player.getField().getTile(target);
        if (!targetTile.getElements().isEmpty())
            return new Result(false, "You must clear the tile first!");
        if (targetTile.getTerrainType() != TerrainType.PLOWED_DIRT)
            return new Result(false, "You must plow the ground first!");

        Planted plant;
        if (seed.isCropSeed()) {
            plant = new PlantedCrop(CropMetaData.getCrop(seed));
        } else {
            plant = new PlantedTree(TreeMetaData.getTree(seed));
        }
        targetTile.addElement(plant);
        game.getTimeState().addObserver(plant);

        return new Result(true, "Seed planted successfully!");
    }

    private Result processShowingPlant(String command) {
        String[] parts = command.split("\\s+-y\\s+|\\s+-x\\s+");
        Coordinate target = new Coordinate(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        Tile targetTile = game.getCurrentPlayer().getField().getTile(target);
        for (MapElement element : targetTile.getElements()) {
            if (element instanceof Planted plant) {
                return new Result(true, plant.toString());
            }
        }
        return new Result(false, "There is no plant at this location!");
    }

    private Result showWateringCan() {
        Player player = game.getCurrentPlayer();
        WateringCan wateringCan = (WateringCan) player.getInventory().findItem("WateringCan");
        if (wateringCan == null) return new Result(false, "Watering Can not found!");
        return new Result(true, "" + wateringCan.getWaterLevel());
    }
}

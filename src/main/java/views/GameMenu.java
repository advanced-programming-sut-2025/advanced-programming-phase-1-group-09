package views;

import models.DataManagers.CropMetaData;
import controllers.GameMenuController;
import models.DataManagers.TreeMetaData;
import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Farming.CropDefinition;
import models.GameWorld.Farming.Planted;
import models.GameWorld.Farming.TreeDefinition;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Miscellaneous.InventorySlot;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Map.Elements.MapElement;
import models.GameWorld.Map.Tile;
import models.Result;

import java.util.Scanner;

public class GameMenu implements AppMenu {
    private final GameMenuController controller;
    private Scanner scanner;
    private Game game;

    public GameMenu() {
        this.controller = new GameMenuController();
        this.scanner = null;
        this.game = null;
    }

    @Override
    public void check(Scanner scanner) {
        this.scanner = scanner;
        String input = scanner.nextLine().trim();
        Result result = controller.processCommand(input);
        System.out.println(result.message());
    }

    /**
     * We should set the menu game before accessing it
     * This should be done for every new game and load game
     */
    public void setGame(Game game) {
        this.game = game;
        controller.setGame(game);
    }

    public static void showPlayerInventory(Player player) {
        System.out.print(ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD +
                "        ITEM        " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLACK_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BOLD_BRIGHT +
                " QUANTITY " + ConsoleColors.RESET);

        for (InventorySlot slot : player.getInventory().getMainInventory().getSlots()) {
            Item item = slot.item();
            String quantity = item.isStackable() ? String.valueOf(slot.quantity()) : "----";

            System.out.println(String.format("%-20s   %s", item.getName(), quantity));
        }
    }

    public static void showPlayerTools(Player player) {
        for (InventorySlot slot : player.getInventory().getMainInventory().getSlots()) {
            if (slot.item() instanceof Tool tool) {
                System.out.println(tool.getName());
            }
        }
    }

    public static void showAllCrops() {
        for (CropDefinition cropDefinition : CropMetaData.getAllCrops()) {
            System.out.println(cropDefinition.name());
        }
    }

    public static void showAllTrees() {
        for (TreeDefinition treeDefinition : TreeMetaData.getAllTrees()) {
            System.out.println(treeDefinition.getName());
        }
    }

    public static void showPlant(Player player, Coordinate position) {
        if (!player.getFarm().isCoordinateWithinMap(position)) {
            System.out.println("Invalid coordinate.");
            return;
        }

        Tile tile = player.getFarm().getTile(position);
        for (MapElement e : tile.getElements()) {
            if (e instanceof Planted plant) {
                System.out.println(plant.toString());
                return;
            }
        }
        System.out.println("There is no plant here.");
    }
}

package views;

import models.App;
import models.DataManagers.CropMetaData;
import controllers.GameMenuController;
import models.DataManagers.TreeMetaData;
import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Gift;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Entity.Player.PlayerFriendship;
import models.GameWorld.Farming.CropDefinition;
import models.GameWorld.Farming.Planted;
import models.GameWorld.Farming.TreeDefinition;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Miscellaneous.InventorySlot;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Map.Elements.MapElement;
import models.GameWorld.Map.Tile;
import models.Result;

import java.util.ArrayList;
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
        App.getInstance().setCurrentGame(game);
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
        if (!player.getField().isCoordinateWithinMap(position)) {
            System.out.println("Invalid coordinate.");
            return;
        }

        Tile tile = player.getField().getTile(position);
        for (MapElement e : tile.getElements()) {
            if (e instanceof Planted plant) {
                System.out.println(plant.toString());
                return;
            }
        }
        System.out.println("There is no plant here.");
    }

    public static void showFriendship(Player player) {
        if (player.getFriendships().isEmpty()) {
            System.out.println("You have no friends");
            return;
        }
        for (PlayerFriendship friendship : player.getFriendships().values()) {
            System.out.println(friendship.toString());
        }
    }

    public static void showNewMessages(Player current, Player friend) {
        PlayerFriendship friendship = current.getFriendships().get(friend.getUsername());
        if (friendship.countNewMessages() == 0) {
            System.out.println("You have no new messages.");
            return;
        }
        for (String message : friendship.getNewMessages()) {
            System.out.printf("\"%s\": %s\n", friend.getUsername(), message);
        }
    }

    public static void showMessages(Player current, Player friend) {
        PlayerFriendship friendship = current.getFriendships().get(friend.getUsername());
        ArrayList<String> messages = friendship.getMessageHistory();
        if (messages.isEmpty()) {
            System.out.println("You have no messages.");
            return;
        }
        for (String message : messages) {
            System.out.printf("\"%s\": %s\n", friend.getUsername(), message);
        }
    }

    public static void showReceivedGifts(Player player) {
        for (PlayerFriendship friendship : player.getFriendships().values()) {
            System.out.println("Sender: " + friendship.getEntity().getName());
            boolean received = false;
            for (Gift gift : friendship.getReceivedGifts()) {
                System.out.println(gift.toString());
                received = true;
            }
            if (!received) System.out.println("You have no received gifts from this sender.");
            System.out.println();
        }
    }

    public static void showGiftHistory(Player current, Player friend) {
        System.out.println("Gift History");
        System.out.println("Sent Gifts:");
        boolean sent = false;
        for (PlayerFriendship friendship : current.getFriendships().values()) {
            for (Gift gift : friendship.getSentGifts()) {
                System.out.println(gift.toString());
                sent = true;
            }
        }
        if (!sent) System.out.println("You have no sent gifts.");

        System.out.println("Received Gifts:");
        boolean received = false;
        for (PlayerFriendship friendship : current.getFriendships().values()) {
            for (Gift gift : friendship.getReceivedGifts()) {
                System.out.println(gift.toString());
                received = true;
            }
        }
        if (!received) System.out.println("You have no received gifts.");
    }
}

package views;

import models.DataManagers.CropMetaData;
import models.DataManagers.TreeMetaData;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Farming.*;
import models.GameWorld.Items.Minerals.Stone;
import models.GameWorld.Map.Elements.MapElement;
import models.GameWorld.Map.Elements.Prefabs.GreenHouse;
import models.GameWorld.Map.Elements.Prefabs.Hut;
import models.GameWorld.Map.Elements.Prefabs.Lake;
import models.GameWorld.Map.Elements.Prefabs.Quarry;
import models.GameWorld.Map.Elements.Rock;
import models.GameWorld.Map.GameMap;
import models.GameWorld.Map.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapPrinter {
    private final static HashMap<String, MapElement> elements = new HashMap<>();
    static {
        elements.put("Rock", new Rock());
        elements.put("GreenHouse", new GreenHouse());
        elements.put("Hut", new Hut());
        elements.put("Lake", new Lake(0, 0, 0, 0));
        elements.put("Quarry", new Quarry());
        elements.put("Seed", new Seed("Seed", 0, 0));
        elements.put("Wood", new Wood());
        elements.put("Stone", new Stone());
        elements.put("Crop", new PlantedCrop(CropMetaData.getCrop("Carrot")));
        elements.put("Tree", new PlantedTree(TreeMetaData.getTree("Oak Tree")));
    }

    public static void printFarm(Player player, int size) {
        System.out.println(player.getName() + "'s Farm");
        GameMap farm = player.getFarm();
        int bound = (size < 10) ? 10 : size / 2;

        Coordinate playerPosition = player.getCoordinate();
        System.out.println("Player Position: " + playerPosition);

        int startY = Math.max(0, playerPosition.y() - bound);
        int endY = Math.min(farm.getHeight() - 1, playerPosition.y() + bound);

        int startX = Math.max(0, playerPosition.x() - bound);
        int endX = Math.min(farm.getWidth() - 1, playerPosition.x() + bound);

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                if ((y == playerPosition.y()) && (x == playerPosition.x())) {
                    System.out.print(ConsoleColors.RED_BOLD_BRIGHT + "☺" + ConsoleColors.RESET + " ");
                    continue;
                }

                Tile tile = farm.getTile(y, x);
                System.out.print(tile.getDisplaySymbol() + " " + ConsoleColors.RESET);
            }
            System.out.println();
        }
    }

    public static void help() {
        System.out.print(
                ConsoleColors.BLACK_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BOLD_BRIGHT +
                        "    ELEMENT    " +
                        ConsoleColors.RESET
        );
        System.out.println(
                ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD +
                " SYMBOL " +
                ConsoleColors.RESET
        );

        for (Map.Entry<String, MapElement> e : elements.entrySet()) {
            System.out.printf("%-15s", e.getKey());
            System.out.println("   " + e.getValue().getSymbol() + ConsoleColors.RESET + "     ");
            System.out.println();
        }
    }
}

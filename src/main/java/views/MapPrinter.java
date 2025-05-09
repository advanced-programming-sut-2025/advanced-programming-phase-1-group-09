package views;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.GameMap;
import models.GameWorld.Map.Tile;

public class MapPrinter {
    public static void printFarm(Player player, int size) {
        GameMap farm = player.getFarm();
        int bound = (size < 10) ? 10 : size / 2;

        Coordinate playerPosition = player.getCoordinate();

        for (int y = playerPosition.y() - bound; y < playerPosition.y() + bound; y++) {
            for (int x = playerPosition.x() - bound; x < playerPosition.x() + bound; x++) {
                if (y == playerPosition.y() && x == playerPosition.x()) {
                    System.out.print(ConsoleColors.RED_BOLD_BRIGHT + "☺" + ConsoleColors.RESET);
                    continue;
                }

                Tile tile = farm.getTile(y, x);
                if (tile == null) continue;
                System.out.print(tile.getDisplaySymbol() + ConsoleColors.RESET);
            }
            System.out.println();
        }
    }
}

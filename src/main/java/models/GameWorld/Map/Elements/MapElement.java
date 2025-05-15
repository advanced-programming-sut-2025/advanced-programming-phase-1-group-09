package models.GameWorld.Map.Elements;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import views.ConsoleColors;

public interface MapElement {
    String getName();
    boolean isInteractable();
    void interact(Player player, Coordinate position);

    default String getSymbol() {
        return ConsoleColors.YELLOW_BACKGROUND + " ";
    }
}

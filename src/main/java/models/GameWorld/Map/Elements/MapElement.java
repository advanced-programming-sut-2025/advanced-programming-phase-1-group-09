package models.GameWorld.Map.Elements;

import models.GameWorld.Entity.Player.Player;
import views.ConsoleColors;

public interface MapElement {
    String getName();
    boolean isInteractable();
    void interact(Player player);

    default boolean isFixed() {
        return false;
    }

    default String getSymbol() {
        return ConsoleColors.YELLOW_BACKGROUND + " ";
    }
}

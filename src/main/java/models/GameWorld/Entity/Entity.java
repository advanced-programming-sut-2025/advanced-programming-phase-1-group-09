package models.GameWorld.Entity;

import models.GameWorld.Entity.Player.Player;

public interface Entity {
    String getName();
    boolean isInteractable();
    void interact(Player player);

    default boolean isFixed() {
        return false;
    }

    default String getSymbol() {
        return "·";
    }
}

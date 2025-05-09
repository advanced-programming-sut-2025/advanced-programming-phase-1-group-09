package models.GameWorld.Map.Elements;

import models.GameWorld.Entity.Player.Player;

public class Rock implements MapElement {
    @Override
    public String getName() {
        return "Rock";
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void interact(Player player) {}

    @Override
    public String getSymbol() {
        return "■";
    }
}

package models.GameWorld.Map.Elements.Prefabs;

import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.Elements.MultiTileElement;
import views.ConsoleColors;

public class Quarry implements MultiTileElement {
    @Override
    public int getHeight() {
        return 10;
    }

    @Override
    public int getWidth() {
        return 10;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public String getName() {
        return "Quarry";
    }

    @Override
    public boolean isFixed() {
        return true;
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void interact(Player player) {}

    @Override
    public String getSymbol() {
        return ConsoleColors.BLACK_BACKGROUND +  "Q";
    }
}

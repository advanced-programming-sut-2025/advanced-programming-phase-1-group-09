package models.GameWorld.Map.Elements.Prefabs;

import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.Elements.MultiTileElement;
import views.ConsoleColors;

public class Hut implements MultiTileElement {
    private final int y;
    private final int x;

    public Hut() {
        this.y = 10;
        this.x = 76;
    }

    @Override
    public int getHeight() {
        return 8;
    }

    @Override
    public int getWidth() {
        return 8;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public String getName() {
        return "Hut";
    }

    @Override
    public boolean isFixed() {
        return true;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void interact(Player player) {}

    @Override
    public String getSymbol() {
        return ConsoleColors.GREEN_BACKGROUND +  "#";
    }
}

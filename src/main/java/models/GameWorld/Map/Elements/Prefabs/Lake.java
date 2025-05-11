package models.GameWorld.Map.Elements.Prefabs;

import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.Elements.MultiTileElement;
import views.ConsoleColors;

public class Lake implements MultiTileElement {
    private final int height;
    private final int width;
    private final int y;
    private final int x;

    public Lake(int height, int width, int y, int x) {
        this.height = height;
        this.width = width;
        this.y = y;
        this.x = x;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
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
        return "Lake";
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
        return ConsoleColors.BLUE_BACKGROUND_BRIGHT +  "L";
    }
}

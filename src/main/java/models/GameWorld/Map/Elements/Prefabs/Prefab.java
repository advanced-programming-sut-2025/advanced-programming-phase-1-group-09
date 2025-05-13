package models.GameWorld.Map.Elements.Prefabs;

import models.GameWorld.Map.Elements.MultiTileElement;

public abstract class Prefab implements MultiTileElement {
    protected final int height;
    protected final int width;
    protected final int y;
    protected final int x;

    public Prefab(int height, int width, int y, int x) {
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
}

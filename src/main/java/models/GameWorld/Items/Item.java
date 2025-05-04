package models.GameWorld.Items;

import models.GameWorld.Coordinate;

public abstract class Item {
    protected final String name;
    protected final boolean isStackable;
    // protected final Coordinate coordinate;
    // Add coordinate later if needed

    public Item(String name, boolean isStackable) {
        this.name = name;
        this.isStackable = isStackable;
    }

    public String getName() {
        return name;
    }

    public boolean isStackable() {
        return isStackable;
    }
}

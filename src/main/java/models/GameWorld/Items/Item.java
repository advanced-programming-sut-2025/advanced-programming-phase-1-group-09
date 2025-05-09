package models.GameWorld.Items;

public abstract class Item {
    protected final String name;
    protected final boolean isStackable;

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

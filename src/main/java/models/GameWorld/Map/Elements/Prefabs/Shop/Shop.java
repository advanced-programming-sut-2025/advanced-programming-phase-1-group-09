package models.GameWorld.Map.Elements.Prefabs.Shop;

import models.GameWorld.Map.Elements.Prefabs.Prefab;

public abstract class Shop extends Prefab {
    protected final String owner;
    protected final int startingHour;
    protected final int endingHour;
    // protected final HashMap<String, InventorySlot> items;

    public Shop(int height, int width, int y, int x, String owner, int startingHour, int endingHour) {
        super(height, width, y, x);
        this.owner = owner;
        this.startingHour = startingHour;
        this.endingHour = endingHour;
        // items = new HashMap<>();
    }

    public String getOwner() {
        return owner;
    }

    public int getStartingHour() {
        return startingHour;
    }

    public int getEndingHour() {
        return endingHour;
    }
}

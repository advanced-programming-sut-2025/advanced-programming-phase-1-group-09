package models.GameWorld.Items.Minerals;

import models.GameWorld.Items.Inventory;
import models.GameWorld.Items.Item;

public class Mineral extends Item {
    private final String description;
    private final int sellPrice;
    private Inventory inventory;

    public Mineral(String description, int sellPrice) {
        this.description = description;
        this.sellPrice = sellPrice;
    }
}

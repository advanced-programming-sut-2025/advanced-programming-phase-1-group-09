package models.GameWorld.Items.Minerals;

import models.GameWorld.Items.Miscellaneous.Inventory;
import models.GameWorld.Items.StackableItem;

public class Mineral extends StackableItem {
    private final String description;
    private Inventory inventory;

    public Mineral(String name, int quantity, String description, int sellPrice) {
        super(name, sellPrice);
        this.description = description;
    }
}

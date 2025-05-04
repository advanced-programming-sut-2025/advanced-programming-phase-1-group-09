package models.GameWorld.Items.Fertilizers;

import models.GameWorld.Items.Miscellaneous.Inventory;
import models.GameWorld.Items.Item;

public class Fertilizer extends Item {
    private Inventory inventory;

    public Fertilizer(String name) {
        super(name, false);
    }
}

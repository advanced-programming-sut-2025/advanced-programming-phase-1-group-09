package models.GameWorld.Items.Edibles;

import models.GameWorld.Items.Miscellaneous.Inventory;
import models.GameWorld.Items.StackableItem;

public class Edible extends StackableItem {
    private final int gainedEnergy;
    private Inventory inventory;

    public Edible(String name, int quantity, int gainedEnergy, int sellPrice) {
        super(name, sellPrice);
        this.gainedEnergy = gainedEnergy;
    }
}

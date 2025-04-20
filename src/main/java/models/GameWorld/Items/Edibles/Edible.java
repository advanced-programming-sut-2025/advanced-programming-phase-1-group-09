package models.GameWorld.Items.Edibles;

import models.GameWorld.Items.Inventory;

public class Edible {
    private final int gainedEnergy;
    private final int sellPrice;
    private Inventory inventory;

    public Edible(int gainedEnergy, int sellPrice) {
        this.gainedEnergy = gainedEnergy;
        this.sellPrice = sellPrice;
    }
}

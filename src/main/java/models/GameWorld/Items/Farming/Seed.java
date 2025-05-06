package models.GameWorld.Items.Farming;

import models.GameWorld.Items.StackableItem;

public class Seed extends StackableItem {
    // We can access the related crop via CropMetaData

    // This will be needed for foraging
    private final double spawningChance;

    public Seed(String name, int price, double spawningChance) {
        super(name, price);
        this.spawningChance = spawningChance;
    }

    public double getSpawningChance() {
        return spawningChance;
    }
}

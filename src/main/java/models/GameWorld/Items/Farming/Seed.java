package models.GameWorld.Items.Farming;

import models.GameWorld.Items.StackableItem;

public class Seed extends StackableItem {
    // We can access the related crop via CropMetaData
    public Seed(String name, int price) {
        super(name, price);
    }
}

package models.GameWorld.Items.Seeds.ForagingSeeds;

import models.GameWorld.Items.Edibles.Crops.Crop;
import models.GameWorld.Items.Seeds.Seed;

public class ForagingSeed extends Seed {
    private final Crop cropGiven;

    public ForagingSeed(String name, int quantity, int price, Crop cropGiven) {
        super(name, quantity, price);
        this.cropGiven = cropGiven;
    }
}

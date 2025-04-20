package models.GameWorld.Items.seeds.ForagingSeeds;

import models.GameWorld.Items.Edibles.Crops.Crop;
import models.GameWorld.Items.seeds.Seed;

public class ForagingSeed extends Seed {
    private final Crop cropGiven;

    public ForagingSeed(Crop cropGiven) {
        this.cropGiven = cropGiven;
    }
}

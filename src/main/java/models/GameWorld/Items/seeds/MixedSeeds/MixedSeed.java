package models.GameWorld.Items.seeds.MixedSeeds;

import models.GameWorld.Items.Edibles.Crops.Crop;
import models.GameWorld.Items.seeds.Seed;

import java.util.ArrayList;

public class MixedSeed extends Seed {
    private final ArrayList<Crop> possibleCropsGiven;

    public MixedSeed(ArrayList<Crop> possibleCropsGiven) {
        this.possibleCropsGiven = possibleCropsGiven;
    }
}

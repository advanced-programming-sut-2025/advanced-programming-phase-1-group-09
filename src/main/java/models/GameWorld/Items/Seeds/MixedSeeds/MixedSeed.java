package models.GameWorld.Items.Seeds.MixedSeeds;

import models.GameWorld.Items.Edibles.Crops.Crop;
import models.GameWorld.Items.Seeds.Seed;

import java.util.ArrayList;

public class MixedSeed extends Seed {
    private final ArrayList<Crop> possibleCropsGiven;


    public MixedSeed(String name, int quantity, int price, ArrayList<Crop> possibleCropsGiven) {
        super(name, quantity, price);
        this.possibleCropsGiven = possibleCropsGiven;
    }
}

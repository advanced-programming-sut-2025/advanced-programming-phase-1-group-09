package models.GameWorld.Items.Edibles.Crops.ForagingCrops;

import models.GameWorld.Items.Edibles.Crops.Crop;
import models.GameWorld.Season;

import java.util.ArrayList;

public class ForagingCrop extends Crop {
    public ForagingCrop(String resource, ArrayList<Integer> stage, int sellPrice, int gainedEnergy,
                        int totalTimeToGrow, boolean isMultiHarvestable, int regrowthTime, boolean isEdible,
                        Season season, boolean canBecomeGiant) {
        super(resource, stage, sellPrice, gainedEnergy,
                totalTimeToGrow, isMultiHarvestable, regrowthTime, isEdible, season, canBecomeGiant);
    }
}

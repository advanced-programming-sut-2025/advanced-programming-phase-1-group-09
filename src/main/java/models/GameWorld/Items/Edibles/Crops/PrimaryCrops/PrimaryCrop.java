package models.GameWorld.Items.Edibles.Crops.PrimaryCrops;

import models.GameWorld.Items.Edibles.Crops.Crop;
import models.GameWorld.Season;

import java.util.ArrayList;

public class PrimaryCrop extends Crop {
    public PrimaryCrop(String resource, ArrayList<Integer> stage, int sellPrice, int gainedEnergy,
                       int totalTimeToGrow, boolean isMultiHarvestable, int regrowthTime, boolean isEdible,
                       Season season, boolean canBecomeGiant) {
        super(resource, stage, sellPrice, gainedEnergy,
                totalTimeToGrow, isMultiHarvestable, regrowthTime, isEdible, season, canBecomeGiant);
    }
}

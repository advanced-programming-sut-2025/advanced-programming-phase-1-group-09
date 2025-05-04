package models.GameWorld.Items.Edibles.Crops.PrimaryCrops;

import models.GameWorld.Items.Edibles.Crops.Crop;
import models.GameWorld.Season;

import java.util.ArrayList;

public class PrimaryCrop extends Crop {

    public PrimaryCrop(String name, int quantity, int gainedEnergy, int sellPrice,
                       String resource, ArrayList<Integer> stage, int totalTimeToGrow, boolean isMultiHarvestable,
                       int regrowthTime, boolean isEdible, Season season, boolean canBecomeGiant) {
        super(name, quantity, gainedEnergy, sellPrice,
              resource, stage, totalTimeToGrow,
              isMultiHarvestable, regrowthTime, isEdible, season, canBecomeGiant);
    }
}

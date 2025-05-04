package models.GameWorld.Items.Edibles.Crops;

import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Season;

import java.util.ArrayList;

public class Crop extends Edible {
    private int timePassedAfterPlanting;
    private final String resource;
    private final ArrayList<Integer> stage;
    private final int totalTimeToGrow;
    private final boolean isMultiHarvestable;
    private final int regrowthTime;
    private final boolean isEdible;
    private final Season season;
    private final boolean canBecomeGiant;


    public Crop(String name, int quantity, int gainedEnergy, int sellPrice,
                String resource, ArrayList<Integer> stage, int totalTimeToGrow, boolean isMultiHarvestable,
                int regrowthTime, boolean isEdible, Season season, boolean canBecomeGiant) {
        super(name, quantity, gainedEnergy, sellPrice);
        this.resource = resource;
        this.stage = stage;
        this.totalTimeToGrow = totalTimeToGrow;
        this.isMultiHarvestable = isMultiHarvestable;
        this.regrowthTime = regrowthTime;
        this.isEdible = isEdible;
        this.season = season;
        this.canBecomeGiant = canBecomeGiant;
    }
}

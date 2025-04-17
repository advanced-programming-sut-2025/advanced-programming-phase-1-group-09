package models.GameWorld.Items.Edibles.Crops;

import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Season;

import java.util.ArrayList;

public class Crop extends Edible {
    private int timePassedAfterPlanting;
    private final String resource;
    private final ArrayList<Integer> stage;
    private final int sellPrice;
    private final int totalTimeToGrow;
    private final boolean isMultiHarvestable;
    private final int regrowthTime;
    private final boolean isEdible;
    private final Season season;
    private final boolean canBecomeGiant;
}

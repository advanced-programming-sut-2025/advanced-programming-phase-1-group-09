package models.GameWorld.Items.Edibles.Fruits;

import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Season;

public class Fruit extends Edible {
    private final int fruitHarvestCycle;
    private final Season season;

    public Fruit(int gainedEnergy, int sellPrice, int fruitHarvestCycle, Season season) {
        super(gainedEnergy, sellPrice);
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.season = season;
    }
}

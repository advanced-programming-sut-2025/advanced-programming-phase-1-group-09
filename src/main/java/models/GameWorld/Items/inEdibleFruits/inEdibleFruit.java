package models.GameWorld.Items.inEdibleFruits;

import models.GameWorld.Items.Inventory;
import models.GameWorld.Season;

public class inEdibleFruit {
    private final int fruitHarvestCycle;
    private final int sellPrice;
    private final Season season;
    private Inventory inventory;

    public inEdibleFruit(int fruitHarvestCycle, int sellPrice, Season season) {
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.sellPrice = sellPrice;
        this.season = season;
    }
}

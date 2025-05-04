package models.GameWorld.Items.inEdibleFruits;

import models.GameWorld.Items.Miscellaneous.Inventory;
import models.GameWorld.Items.StackableItem;
import models.GameWorld.Season;

public class inEdibleFruit extends StackableItem {
    private final int fruitHarvestCycle;
    private final Season season;
    private Inventory inventory;

    public inEdibleFruit(String name, int quantity, int fruitHarvestCycle, int sellPrice, Season season) {
        super(name, quantity, sellPrice);
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.season = season;
    }
}

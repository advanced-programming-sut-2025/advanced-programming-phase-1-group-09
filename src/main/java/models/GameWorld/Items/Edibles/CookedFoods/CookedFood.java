package models.GameWorld.Items.Edibles.CookedFoods;

import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Items.StackableItem;

public class CookedFood extends StackableItem implements Edible {
    private final int gainedEnergy;

    public CookedFood(String name, int gainedEnergy, int sellPrice) {
        super(name, sellPrice);
        this.gainedEnergy = gainedEnergy;
    }

    @Override
    public int getGainedEnergy() {
        return gainedEnergy;
    }
}

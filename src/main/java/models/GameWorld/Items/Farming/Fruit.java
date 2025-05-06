package models.GameWorld.Items.Farming;

import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Items.StackableItem;

public class Fruit extends StackableItem implements Edible {
    private final int gainedEnergy;

    public Fruit(String name, int gainedEnergy, int sellPrice) {
        super(name, sellPrice);
        this.gainedEnergy = gainedEnergy;
    }

    @Override
    public int getGainedEnergy() {
        return gainedEnergy;
    }
}

package models.GameWorld.Farming;

import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.StackableItem;
import models.GameWorld.Map.Elements.Collectable.Collectable;

public class Fruit extends StackableItem implements Edible, Collectable {
    private final int gainedEnergy;

    public Fruit(String name, int gainedEnergy, int sellPrice) {
        super(name, sellPrice);
        this.gainedEnergy = gainedEnergy;
    }

    @Override
    public int getGainedEnergy() {
        return gainedEnergy;
    }

    @Override
    public Item collect() {
        return this;
    }
}

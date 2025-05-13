package models.GameWorld.Farming;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Items.Item;
import models.GameWorld.Map.Elements.Collectable.Collectable;

public class Fruit extends Item implements Edible, Collectable {
    private final int gainedEnergy;

    public Fruit(String name, int gainedEnergy, int sellPrice) {
        super(name, true, sellPrice);
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

    @Override
    public void use(Coordinate target, Player player, Game game) {}

    @Override
    public Fruit clone() {
        return new Fruit(name, gainedEnergy, price);
    }
}

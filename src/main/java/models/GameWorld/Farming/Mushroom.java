package models.GameWorld.Farming;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Items.Item;
import models.GameWorld.Map.Elements.Collectable.Collectable;

public class Mushroom extends Item implements Edible, Collectable {
    private final int gainedEnergy;

    public Mushroom(String name, boolean isStackable, int price, int gainedEnergy) {
        super(name, isStackable, price);
        this.gainedEnergy = gainedEnergy;
    }

    @Override
    public int getGainedEnergy() {
        return gainedEnergy;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }

    @Override
    public Item collect() {
        return null;
    }

    @Override
    public Mushroom clone() {return new Mushroom(name, isStackable, price, gainedEnergy);}
}

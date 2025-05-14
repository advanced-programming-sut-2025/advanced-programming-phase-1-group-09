package models.GameWorld.Items.Recipes.CookingRecipe;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Items.Item;


public class CookedItem extends Item implements Edible {
    private final int gainedEnergy;
    public CookedItem(String name, boolean isStackable, int price, int gainedEnergy) {
        super(name, isStackable, price);
        this.gainedEnergy = gainedEnergy;
    }

    @Override
    public CookedItem clone() {return new CookedItem(name, isStackable, price, gainedEnergy);}

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }

    @Override
    public int getGainedEnergy() {
        return gainedEnergy;
    }
}

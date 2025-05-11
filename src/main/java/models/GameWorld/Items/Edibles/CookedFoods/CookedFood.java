package models.GameWorld.Items.Edibles.CookedFoods;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Items.Item;

public class CookedFood extends Item implements Edible {
    private final int gainedEnergy;

    public CookedFood(String name, int gainedEnergy, int sellPrice) {
        super(name, true, sellPrice);
        this.gainedEnergy = gainedEnergy;
    }

    @Override
    public int getGainedEnergy() {
        return gainedEnergy;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

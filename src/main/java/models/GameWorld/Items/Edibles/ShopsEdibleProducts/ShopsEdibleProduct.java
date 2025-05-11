package models.GameWorld.Items.Edibles.ShopsEdibleProducts;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Items.Item;

public class ShopsEdibleProduct extends Item implements Edible {
    private final int gainedEnergy;

    public ShopsEdibleProduct(String name, int gainedEnergy, int sellPrice) {
        super(name, true, sellPrice);
        this.gainedEnergy = gainedEnergy;
    }

    public int getGainedEnergy() {
        return gainedEnergy;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

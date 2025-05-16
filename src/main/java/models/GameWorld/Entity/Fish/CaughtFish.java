package models.GameWorld.Entity.Fish;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;

public class CaughtFish extends Item {

    public CaughtFish(String name, boolean isStackable, int price) {
        super(name, isStackable, price);
    }

    @Override
    public CaughtFish clone() {return new CaughtFish(name, isStackable, price);}

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

package models.GameWorld.Items.Edibles.AnimalEdibleProduct;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.StackableItem;

public class AnimalEdibleProduct extends StackableItem {

    public AnimalEdibleProduct(String name, int quantity, int price) {
        super(name, quantity, price);
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

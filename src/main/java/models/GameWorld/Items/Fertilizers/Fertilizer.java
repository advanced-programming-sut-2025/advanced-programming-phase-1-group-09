package models.GameWorld.Items.Fertilizers;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Miscellaneous.Inventory;
import models.GameWorld.Items.Item;

public class Fertilizer extends Item {
    private Inventory inventory;

    public Fertilizer(String name, int price) {
        super(name, false, price);
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

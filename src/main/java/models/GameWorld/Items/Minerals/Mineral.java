package models.GameWorld.Items.Minerals;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Miscellaneous.Inventory;

public class Mineral extends Item {
    private final String description;
    private Inventory inventory;

    public Mineral(String name, int quantity, String description, int sellPrice) {
        super(name, true, sellPrice);
        this.description = description;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

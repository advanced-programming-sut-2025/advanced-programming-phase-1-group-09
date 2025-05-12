package models.GameWorld.Items.inEdibleFruits;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Miscellaneous.Inventory;
import models.GameWorld.Season;

public class inEdibleFruit extends Item {
    private final int fruitHarvestCycle;
    private final Season season;
    private Inventory inventory;

    public inEdibleFruit(String name, int quantity, int fruitHarvestCycle, int sellPrice, Season season) {
        super(name, true, sellPrice);
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.season = season;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

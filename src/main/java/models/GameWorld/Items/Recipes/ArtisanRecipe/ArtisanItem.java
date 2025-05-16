package models.GameWorld.Items.Recipes.ArtisanRecipe;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;

public class ArtisanItem extends Item {

    public ArtisanItem(String name, boolean isStackable, int price) {
        super(name, isStackable, price);
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

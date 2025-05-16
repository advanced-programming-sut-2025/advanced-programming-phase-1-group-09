package models.GameWorld.Items.Recipes.CraftingRecipe;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;

public class CraftedItem extends Item {

    public CraftedItem(String name, boolean isStackable, int price) {
        super(name, isStackable, price);
    }

    @Override
    public CraftedItem clone() {
        return (CraftedItem) super.clone();
    }



    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

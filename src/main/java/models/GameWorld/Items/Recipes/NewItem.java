package models.GameWorld.Items.Recipes;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;

import java.util.LinkedHashMap;

public class NewItem extends Item {
    private final LinkedHashMap<Item,Integer> Ingredients;

    public NewItem(String name, int price, LinkedHashMap<Item, Integer> ingredients) {
        super(name, false, price);
        Ingredients = ingredients;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

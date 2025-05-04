package models.GameWorld.Items.Recipes;

import models.GameWorld.Items.Item;

import java.util.LinkedHashMap;

public class NewItem extends Item {
    private final int sellPrice;
    private final LinkedHashMap<Item,Integer> Ingredients;

    public NewItem(String name, int sellPrice, LinkedHashMap<Item, Integer> ingredients) {
        super(name, false);
        this.sellPrice = sellPrice;
        Ingredients = ingredients;
    }
}

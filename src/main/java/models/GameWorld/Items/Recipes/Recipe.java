package models.GameWorld.Items.Recipes;

import models.GameWorld.Items.Item;
import models.GameWorld.Items.StackableItem;

import java.util.List;

public class Recipe {
    private final StackableItem result;
    private final List<Item> ingredients;
    private boolean isUnlocked = false;
    private final int price;

    public Recipe(StackableItem result, List<Item> ingredients, int price) {
        this.result = result;
        this.ingredients = ingredients;
        this.price = price;
    }
}

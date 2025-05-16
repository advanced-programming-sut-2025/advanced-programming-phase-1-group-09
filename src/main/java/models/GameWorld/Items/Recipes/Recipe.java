package models.GameWorld.Items.Recipes;

import models.GameWorld.Items.Item;

import java.util.List;

public class Recipe {
    private final Item result;
    private final List<Ingredient> ingredients;
    private boolean isUnlocked = false;
    private final int price;

    public Recipe(Item result, List<Ingredient> ingredients, int price) {
        this.result = result;
        this.ingredients = ingredients;
        this.price = price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public int getPrice() {
        return price;
    }


    public Item getResult() {
        return result;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }
}

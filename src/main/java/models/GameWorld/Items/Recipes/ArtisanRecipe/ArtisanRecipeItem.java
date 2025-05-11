package models.GameWorld.Items.Recipes.ArtisanRecipe;

import models.GameWorld.Items.Item;

import java.util.LinkedHashMap;

public class ArtisanRecipeItem extends NewItem {
    private final int proccessigTime;
    private final int gainedEnergy;
    private final String description;


    public ArtisanRecipeItem(String name, int sellPrice, LinkedHashMap<Item, Integer> ingredients,
                             int processingTime, int gainedEnergy, String description) {
        super(name, sellPrice, ingredients);
        this.proccessigTime = processingTime;
        this.gainedEnergy = gainedEnergy;
        this.description = description;
    }
}

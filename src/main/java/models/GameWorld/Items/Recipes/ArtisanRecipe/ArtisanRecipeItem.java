package models.GameWorld.Items.Recipes.ArtisanRecipe;

import models.GameWorld.Items.Item;
import models.GameWorld.Items.Recipes.NewItem;

import java.util.LinkedHashMap;

public class ArtisanRecipeItem extends NewItem {
    private final int processigTime;
    private final int gainedEnergy;
    private final String description;

    public ArtisanRecipeItem(int sellPrice, LinkedHashMap<Item, Integer> ingredients,
                             int processigTime, int gainedEnergy, String description) {
        super(sellPrice, ingredients);
        this.processigTime = processigTime;
        this.gainedEnergy = gainedEnergy;
        this.description = description;
    }
}

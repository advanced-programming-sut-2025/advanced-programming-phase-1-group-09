package models.GameWorld.Items.Recipes.CookingRecipe;

import java.util.ArrayList;

public class CookingRecipe {
    private final ArrayList<CookingRecipeItem> items;

    public CookingRecipe(ArrayList<CookingRecipeItem> items) {
        this.items = items;
    }
}

package models.GameWorld.Items.Recipes.CraftingRecipe;

import java.util.ArrayList;

public class CraftingRecipe {
    private final ArrayList<CraftingRecipeItem> items;

    public CraftingRecipe(ArrayList<CraftingRecipeItem> items) {
        this.items = items;
    }
}

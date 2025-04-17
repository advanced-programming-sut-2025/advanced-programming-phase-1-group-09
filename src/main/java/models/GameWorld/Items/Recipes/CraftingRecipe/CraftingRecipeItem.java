package models.GameWorld.Items.Recipes.CraftingRecipe;

import models.GameWorld.Items.Recipes.NewItem;
import models.GameWorld.Map.Buildings.PrefabBuildings.ConstantBuildings.Shops.Shop;
import models.GameWorld.Skills.Skill;

public class CraftingRecipeItem extends NewItem {
    private final String description;
    private final Skill skillSource;
    private final Shop shopSource;
    private final int energyNeeded;
}

package models.GameWorld.Items.Recipes.CraftingRecipe;

import models.GameWorld.Items.Item;
import models.GameWorld.Items.Recipes.NewItem;
import models.GameWorld.Map.Buildings.PrefabBuildings.ConstantBuildings.Shops.Shop;
import models.GameWorld.Skills.Skill;

import java.util.LinkedHashMap;

public class CraftingRecipeItem extends NewItem {
    private final String description;
    private final Skill skillSource;
    private final Shop shopSource;
    private final int energyNeeded;


    public CraftingRecipeItem(String name, int sellPrice, LinkedHashMap<Item, Integer> ingredients,
                              String description, Skill skillSource, Shop shopSource, int energyNeeded) {
        super(name, sellPrice, ingredients);
        this.description = description;
        this.skillSource = skillSource;
        this.shopSource = shopSource;
        this.energyNeeded = energyNeeded;
    }
}

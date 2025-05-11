package models.GameWorld.Items.Recipes.CraftingRecipe;

import models.GameWorld.Items.Item;
import models.GameWorld.Items.Recipes.Recipe;
import models.GameWorld.Items.StackableItem;
import models.GameWorld.Enums.Skills;

import java.util.List;

public class CraftingRecipe extends Recipe {
    private final Skills relatedSkill;
    private final int minimunSkillLevel;
    private final
    public CraftingRecipe(StackableItem result, List<Item> ingredients, int price, ) {
        super(result, ingredients, price);
    }
}

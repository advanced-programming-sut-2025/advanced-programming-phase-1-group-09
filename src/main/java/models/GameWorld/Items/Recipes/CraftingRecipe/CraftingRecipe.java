package models.GameWorld.Items.Recipes.CraftingRecipe;

import models.GameWorld.Enums.Shops;
import models.GameWorld.Enums.Skills;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Recipes.Ingredient;
import models.GameWorld.Items.Recipes.Recipe;

import java.util.List;

public class CraftingRecipe extends Recipe {
    private final Skills skillTypeNeeded;
    private final int skillLevelNeeded;
    private final Shops shopNeeded;
    public CraftingRecipe(Item result, List<Ingredient> ingredients, int price, Skills skillTypeNeeded, int skillLevelNeeded, Shops shopNeeded) {
        super(result,ingredients, price);
        this.skillTypeNeeded = skillTypeNeeded;
        this.skillLevelNeeded = skillLevelNeeded;
        this.shopNeeded = shopNeeded;
    }

    public Skills getSkillTypeNeeded() {
        return skillTypeNeeded;
    }

    public int getSkillLevelNeeded() {
        return skillLevelNeeded;
    }

    public Shops getShopNeeded() {
        return shopNeeded;
    }


    //TODO
    public CraftedItem craft(){
        return null;
    }
}

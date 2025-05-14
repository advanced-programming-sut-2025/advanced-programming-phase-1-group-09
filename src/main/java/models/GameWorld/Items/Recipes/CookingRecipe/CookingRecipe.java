package models.GameWorld.Items.Recipes.CookingRecipe;

import models.GameWorld.Enums.Shops;
import models.GameWorld.Enums.Skills;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Recipes.Ingredient;
import models.GameWorld.Items.Recipes.Recipe;

import java.util.List;

public class CookingRecipe extends Recipe {
    private final Skills skillTypeNeeded;
    private final int skillLevelNeeded;
    private final Shops shopNeeded;
    private final boolean isLeahReward;
    private final int energyGiven;
    public CookingRecipe(Item result, List<Ingredient> ingredients, int price, Skills skillTypeNeeded, int skillLevelNeeded, Shops shopNeeded, boolean isLeahReward, int energyGiven) {
        super(result, ingredients, price);
        this.skillTypeNeeded = skillTypeNeeded;
        this.skillLevelNeeded = skillLevelNeeded;
        this.shopNeeded = shopNeeded;
        this.isLeahReward = isLeahReward;
        this.energyGiven = energyGiven;
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

    public boolean isLeahReward() {
        return isLeahReward;
    }

    public int getEnergyGiven() {
        return energyGiven;
    }
}

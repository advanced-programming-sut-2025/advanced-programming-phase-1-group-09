package models.GameWorld.Items.Recipes.CookingRecipe;

import models.GameWorld.Skills.Skill;

public class CookingRecipeItem {
    private final int gainedEnergy;
    private final Skill skillSource;
    //private final Shop shopSource;

    public CookingRecipeItem(int gainedEnergy, Skill skillSource) {
        this.gainedEnergy = gainedEnergy;
        this.skillSource = skillSource;
    }
}

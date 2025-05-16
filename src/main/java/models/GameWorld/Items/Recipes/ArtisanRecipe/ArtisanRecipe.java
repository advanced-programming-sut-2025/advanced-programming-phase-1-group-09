package models.GameWorld.Items.Recipes.ArtisanRecipe;

import models.GameWorld.Items.Item;
import models.GameWorld.Items.Recipes.Ingredient;
import models.GameWorld.Items.Recipes.Recipe;

import java.util.List;

public class ArtisanRecipe extends Recipe {
    private final Item machine;
    private final int processingTime;
    private final int energyGiven;
    private final String energyMessage;
    private final String priceMessage;
    public ArtisanRecipe(Item result, List<Ingredient> ingredients, int price, Item machine, int energyGiven, int processingTime, String energyMessage, String priceMessage) {
        super(result, ingredients, price);
        this.machine = machine;
        this.energyGiven = energyGiven;
        this.processingTime = processingTime;
        this.energyMessage = energyMessage;
        this.priceMessage = priceMessage;
    }

    public Item getMachine() {
        return machine;
    }

    public int getEnergyGiven() {
        return energyGiven;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public String getEnergyMessage() {
        return energyMessage;
    }

    public String getPriceMessage() {
        return priceMessage;
    }
}

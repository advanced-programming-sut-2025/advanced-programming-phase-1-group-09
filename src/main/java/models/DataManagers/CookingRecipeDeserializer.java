package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Enums.Shops;
import models.GameWorld.Enums.Skills;
import models.GameWorld.Items.Recipes.CookingRecipe.CookingRecipe;
import models.GameWorld.Items.Recipes.Ingredient;

import java.io.IOException;
import java.util.ArrayList;

public class CookingRecipeDeserializer extends JsonDeserializer<CookingRecipe> {
    public CookingRecipe deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("Name").asText();
        int energy = node.get("Energy").asInt();
        int sellPrice = node.get("Sell Price").asInt();
        String[] source = node.get("Source").asText().split(" ");
        String[] ingredients = node.get("Ingredients").asText().split("\\+");
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        for(String ingredient : ingredients){
            ingredient = ingredient.trim();
            String[] details = ingredient.split(",");
            if(details.length == 2)
                ingredientList.add(new Ingredient(DataHolder.getItem(details[1]),Integer.parseInt(details[0]),null));
            else
                ingredientList.add(new Ingredient(null, Integer.parseInt(details[0]),FishMetaData.getCaught()));
        }

        switch (source[0]) {
            case "Starter" : return new CookingRecipe(DataHolder.getItem(name), ingredientList, sellPrice, null, 0, null, false,energy);
            case "Stardrop" : return new CookingRecipe(DataHolder.getItem(name), ingredientList, sellPrice, null, 0, Shops.TheStarDropSaloon, false,energy);
            case "Leah" : return new CookingRecipe(DataHolder.getItem(name), ingredientList, sellPrice, null, 0, null, true,energy);
            case "Foraging" : return new CookingRecipe(DataHolder.getItem(name), ingredientList, sellPrice, Skills.Foraging, Integer.parseInt(source[1]), null, false,energy);
            case "Farming" : return new CookingRecipe(DataHolder.getItem(name), ingredientList, sellPrice, Skills.Farming, Integer.parseInt(source[1]), null, false,energy);
            case "Fishing" : return new CookingRecipe(DataHolder.getItem(name), ingredientList, sellPrice, Skills.Fishing, Integer.parseInt(source[1]), null, false,energy);
            case "Mining" : return new CookingRecipe(DataHolder.getItem(name), ingredientList, sellPrice, Skills.Mining, Integer.parseInt(source[1]), null, false,energy);
            default :
        }
        return null;
    }
}

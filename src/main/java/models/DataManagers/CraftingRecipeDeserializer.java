package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Enums.Shops;
import models.GameWorld.Enums.Skills;
import models.GameWorld.Items.Recipes.CraftingRecipe.CraftingRecipe;
import models.GameWorld.Items.Recipes.Ingredient;

import java.io.IOException;
import java.util.ArrayList;

public class CraftingRecipeDeserializer extends JsonDeserializer<CraftingRecipe> {
    @Override
    public CraftingRecipe deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("Name").asText();
        int sellPrice = node.get("Sell Price").asInt();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (JsonNode ingredient : node.get("Ingredients")) {
            String[] details = ingredient.get("Details").asText().split(",");
            ingredients.add(new Ingredient(DataHolder.getItem(details[0]),Integer.parseInt(details[1])));
        }
        String[] skill = node.get("Skill").asText().split(",");
        if(skill.length == 0)
            return new CraftingRecipe(DataHolder.getItem(name),ingredients,sellPrice,null,0,null);
        else {
            switch (skill[0]) {
                case "Foraging":
                    return new CraftingRecipe(DataHolder.getItem(name), ingredients, sellPrice, Skills.Foraging, Integer.parseInt(skill[1]), null);
                case "Mining":
                    return new CraftingRecipe(DataHolder.getItem(name), ingredients, sellPrice, Skills.Mining, Integer.parseInt(skill[1]), null);
                case "Farming":
                    return new CraftingRecipe(DataHolder.getItem(name), ingredients, sellPrice, Skills.Farming, Integer.parseInt(skill[1]), null);
                default:
                    switch (node.get("Skill").asText()) {
                        case "Pierre's General Store":
                            return new CraftingRecipe(DataHolder.getItem(name), ingredients, sellPrice, null, 0, Shops.PierreGeneralStore);
                        case "Fish Shop":
                            return new CraftingRecipe(DataHolder.getItem(name), ingredients, sellPrice, null, 0, Shops.FishShop);
                        default:
                    }
            }
        }
        return null;
    }
}

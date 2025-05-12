package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Farming.Crop;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Recipes.CraftingRecipe.CraftingRecipe;
import models.GameWorld.Items.Recipes.Ingredient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CraftingRecipeDeserializer extends JsonDeserializer<CraftingRecipe> {
    @Override
    public CraftingRecipe deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("Name").asText();
        int sellPrice = node.get("Sell Price").asInt();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (JsonNode ingredient : node.get("Ingredients")) {
            String[] details = ingredient.get("Details").asText().split(",");
            ingredients.add(new Ingredient(details[0],Integer.parseInt(details[1])));
        }
        return new CraftingRecipe()
    }
}

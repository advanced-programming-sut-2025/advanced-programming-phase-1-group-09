package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Recipes.CraftingRecipe.CraftingRecipe;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CraftingRecipeMetaData {
    private static final List<CraftingRecipe> craftingRecipes = new ArrayList<>();
    private static final List<Item> craftedItems = new ArrayList<>();
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(CraftingRecipe.class, new CraftingRecipeDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = CraftingRecipeMetaData.class.getClassLoader().getResourceAsStream("JSON/crafting_recipe.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<CraftingRecipe> recipesList = mapper.readValue(inputStream, new TypeReference<>() {});
            for(CraftingRecipe recipe : recipesList) {
                craftingRecipes.add(recipe);
                craftedItems.add(recipe.getResult());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load crafting recipes", e);
        }
    }

    public static List<CraftingRecipe> getCraftingRecipes() {
        return craftingRecipes;
    }

    public static List<Item> getCraftedItems() {
        return craftedItems;
    }
}

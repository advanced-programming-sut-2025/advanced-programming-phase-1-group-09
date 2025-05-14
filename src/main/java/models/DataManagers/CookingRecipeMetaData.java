package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Recipes.CookingRecipe.CookingRecipe;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CookingRecipeMetaData {
    private static final List<CookingRecipe> cookingRecipes = new ArrayList<>();
    private static final List<Item> cookedItems = new ArrayList<>();
    static{
        try{
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(CookingRecipe.class, new CookingRecipeDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = CookingRecipeMetaData.class.getClassLoader().getResourceAsStream("JSON/cooking_recipe.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<CookingRecipe> recipesList = mapper.readValue(inputStream, new TypeReference<>() {});
            for(CookingRecipe recipe : recipesList) {
                cookingRecipes.add(recipe);
                cookedItems.add(recipe.getResult());
            }
        }catch (IOException e) {
            throw new RuntimeException("Failed to load cooking recipes", e);
        }
    }

    public static List<CookingRecipe> getCookingRecipes() {
        return cookingRecipes;
    }

    public static List<Item> getCookedItems() {
        return cookedItems;
    }
}

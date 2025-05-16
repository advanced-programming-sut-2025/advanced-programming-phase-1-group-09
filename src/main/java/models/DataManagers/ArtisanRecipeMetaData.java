package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Recipes.ArtisanRecipe.ArtisanRecipe;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ArtisanRecipeMetaData {
    private static final List<ArtisanRecipe> artisanRecipes = new ArrayList<>();
    private static final List<Item> artisanItems = new ArrayList<>();
    static{
        try{
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(ArtisanRecipe.class, new ArtisanRecipeDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = ArtisanRecipeMetaData.class.getClassLoader().getResourceAsStream("JSON/artisan_recipe.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<ArtisanRecipe> recipesList = mapper.readValue(inputStream, new TypeReference<>() {});
            for(ArtisanRecipe recipe : recipesList) {
                artisanRecipes.add(recipe);
                artisanItems.add(recipe.getResult());
            }
        }catch (IOException e) {
            throw new RuntimeException("Failed to load cooking recipes", e);
        }
    }

    public static List<ArtisanRecipe> getArtisanRecipes() {
        return artisanRecipes;
    }

    public static List<Item> getArtisanItems() {
        return artisanItems;
    }
}

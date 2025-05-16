package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Recipes.ArtisanRecipe.ArtisanRecipe;
import models.GameWorld.Items.Recipes.Ingredient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtisanRecipeDeserializer extends JsonDeserializer<ArtisanRecipe> {
    @Override
    public ArtisanRecipe deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        String name = node.get("Name").asText();

        String[] processingTime = node.get("ProcessingTime").asText().split(" ");
        int hourNumber;
        switch (processingTime[1]) {
            case "the":
                hourNumber = -1;
                break;
            case "Hour":
                hourNumber =  Integer.parseInt(processingTime[0]);
                break;
            case "Day":
                hourNumber =  Integer.parseInt(processingTime[0]) * 13;
                break;
            default: hourNumber = -1;
        }

        String[] ingredientText = node.get("Ingredients").asText().split("\\+");
        ArrayList<Ingredient> ingredients =  new ArrayList<>();
        for(String ingredient : ingredientText) {
            if(ingredient.equals("Corn/SunflowerSeed/Sunflower")) {
                List<Item> ingredientList = new ArrayList<>();
                ingredientList.add(DataHolder.getItem("Corn"));
                ingredientList.add(DataHolder.getSeed("Sunflower Seed"));
                ingredientList.add(DataHolder.getSeed("Sunflower")); ////????
                ingredients.add(new Ingredient(null,1,ingredientList));
            }
            else{
                String[] details = ingredient.split(",");
                if(details[0].contains("Any")) {
                    switch (details[0]){
                        case "Any Fish":
                            ingredients.add(new Ingredient(null,Integer.parseInt(details[1]),FishMetaData.getCaught()));
                            break;
                        case "Any Fruit":
                            ingredients.add(new Ingredient(null,Integer.parseInt(details[1]),FruitMetaData.getFruit()));
                            break;
                        case "Any Vegetable":
                            ingredients.add(new Ingredient(null,Integer.parseInt(details[1]),VegetableMetaData.getVegetable()));
                            break;
                        case "Anu Mushroom":
                            ingredients.add(new Ingredient(null,Integer.parseInt(details[1]),MushroomMetaData.getMushroom()));
                            break;
                        default:
                    }
                }
                else
                    ingredients.add(new Ingredient(DataHolder.getItem(details[0]),Integer.parseInt(details[1]),null));
            }
        }

        String energy = node.get("Energy").asText();
        int energyValue;
        if(energy.contains("Base")) {
            energyValue = -1;
        }
        else {
            energyValue = Integer.parseInt(energy);
            energy = null;
        }

        String price = node.get("Price").asText();
        int priceValue;
        if(price.contains("Base")) {
            priceValue = -1;
        }
        else{
            priceValue = Integer.parseInt(price);
            price = null;
        }

        String machine = node.get("Machine").asText();

        return new ArtisanRecipe(DataHolder.getItem(name),ingredients,priceValue,DataHolder.getItem(machine),energyValue,hourNumber,energy,price);
    }
}

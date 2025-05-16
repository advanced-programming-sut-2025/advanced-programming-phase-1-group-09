package models.DataManagers;

import models.GameWorld.Entity.Animals.Animal;
import models.GameWorld.Entity.Animals.BarnAnimal;
import models.GameWorld.Entity.Animals.CoopAnimal;
import models.GameWorld.Entity.Fish.LiveFish;
import models.GameWorld.Items.Item;
import models.GameWorld.Farming.*;
import models.GameWorld.Items.Tools.AchievableTools.Elements.*;
import models.GameWorld.Items.Tools.PrimaryTools.*;
import models.GameWorld.Minerals.UnextractedMineral;

import java.util.HashMap;

public class DataHolder {
    private static final HashMap<String, Item> items = new HashMap<>();
    private static final HashMap<String, Seed> seeds = new HashMap<>();
    private static final HashMap<String, Animal> animals = new HashMap<>();
    private static final HashMap<String, LiveFish> fish = new HashMap<>();
    static {
        // Tools
        items.put("Axe", new Axe());
        items.put("Hoe", new Hoe());
        items.put("Pickaxe", new Pickaxe());
        items.put("Scythe", new Scythe());
        items.put("WateringCan", new WateringCan());
        items.put("FishingPole", new FishingPole());
        items.put("MilkPail", new MilkPail());
        items.put("Shear", new Shear());


        // Tree Seeds
        for (Seed seed : TreeMetaData.getAllSeeds()) {
            items.put(seed.getName(), seed.clone());
            seeds.put(seed.getName(), seed.clone());
        }

        // Crop Seeds
        for (Seed seed : CropMetaData.getAllSeeds()) {
            items.put(seed.getName(), seed.clone());
            items.put(seed.getName(), seed.clone());
        }

        // Foraging Crops
        for (ForagingCrop crop : ForagingCropMetaData.getForagingCrops()) {
            items.put(crop.getName(), crop.clone());
        }

        // Fruit
        for (Item fruit : FruitMetaData.getFruit()) {
            items.put(fruit.getName(), fruit.clone());
        }

        // Vegetable
        for (Item vegetable : VegetableMetaData.getVegetable()){
            items.put(vegetable.getName(), vegetable.clone());
        }

        // Mushroom
        for (Item mushroom : MushroomMetaData.getMushroom()){
            items.put(mushroom.getName(), mushroom.clone());
        }

        // Wood
        items.put("Wood", new Wood());

        // Minerals
        for (UnextractedMineral mineral : MineralMetaData.getAllMinerals()) {
            items.put(mineral.getName(), mineral.extract());
        }

        // Barn Animals
        for (BarnAnimal animal : BarnAnimalMetaData.getBarnAnimals()) {
            animals.put(animal.getName() , animal.clone());
        }

        // Coop Animals
        for(CoopAnimal animal : CoopAnimalMetaData.getCoopAnimals()) {
            animals.put(animal.getName(), animal.clone());
        }

        // LiveFish
        for(LiveFish fishElement : FishMetaData.getFish())
            fish.put(fishElement.getName(), fishElement.clone());

        // CaughtFish
        for(Item fishElement : FishMetaData.getCaught()) {
            items.put(fishElement.getName(), fishElement.clone());
        }

        // Crafted Items
        for(Item craftedItem : CraftingRecipeMetaData.getCraftedItems()) {
            items.put(craftedItem.getName(), craftedItem.clone());
        }

        //Cooked Items
        for(Item cookedItem : CookingRecipeMetaData.getCookedItems()) {
            items.put(cookedItem.getName(), cookedItem.clone());
        }

        //Artisan Items
        for(Item artisanItem : ArtisanRecipeMetaData.getArtisanItems()) {
            items.put(artisanItem.getName(), artisanItem.clone());
        }
    }

    public static Item getItem(String name) {
        return items.get(name);
    }

    public static Seed getSeed(String name) {
        return seeds.get(name);
    }

    public static Animal getAnimal(String name) {return animals.get(name);}

    public static LiveFish getFish(String name) {return fish.get(name);}
}

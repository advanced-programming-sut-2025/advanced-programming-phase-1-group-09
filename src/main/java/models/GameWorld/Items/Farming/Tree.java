package models.GameWorld.Items.Farming;

import models.GameWorld.Enums.SeasonName;

import java.util.ArrayList;
import java.util.Set;

/**
 * The Tree class is used to make different types of trees from JSON file,
 * so you shouldn't instantiate an object everytime you need to add a tree to the game.
 * Instead, you may use PlantedTree to instantiate a crop object.
 */
public final class Tree {
    private final String name;
    private final String source;
    private final ArrayList<Integer> growthStages;
    private final int totalGrowthDays;
    private final Fruit fruit;
    private final int fruitHarvestCycle;
    private final int fruitBaseSellPrice;
    private final boolean isFruitEdible;
    private final int fruitEnergy;
    private final Set<SeasonName> growingSeasons;
    private final double spawningChance;

    public Tree(String name, String source, ArrayList<Integer> growthStages,
                int totalGrowthDays, String fruitName, int fruitHarvestCycle,
                int fruitBaseSellPrice, boolean isFruitEdible, int fruitEnergy,
                Set<SeasonName> growingSeasons, double spawningChance) {
        this.name = name;
        this.source = source;
        this.growthStages = growthStages;
        this.totalGrowthDays = totalGrowthDays;
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.fruitBaseSellPrice = fruitBaseSellPrice;
        this.isFruitEdible = isFruitEdible;
        this.fruitEnergy = fruitEnergy;
        this.fruit = new Fruit(fruitName, fruitEnergy, fruitBaseSellPrice);
        this.growingSeasons = growingSeasons;
        this.spawningChance = spawningChance;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public ArrayList<Integer> getGrowthStages() {
        return growthStages;
    }

    public int getTotalGrowthDays() {
        return totalGrowthDays;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public int getFruitHarvestCycle() {
        return fruitHarvestCycle;
    }

    public int getFruitBaseSellPrice() {
        return fruitBaseSellPrice;
    }

    public boolean isFruitEdible() {
        return isFruitEdible;
    }

    public int getFruitEnergy() {
        return fruitEnergy;
    }

    public Set<SeasonName> getGrowingSeasons() {
        return growingSeasons;
    }

    public double getSpawningChance() {
        return spawningChance;
    }

    public String getStringStages() {
        String stages = "";
        for (Integer stage : growthStages) {
            stages += stage + "-";
        }
        stages = stages.substring(0, stages.length() - 1);
        return stages;
    }

    public String getStringSeasons() {
        String seasons = "";
        for (SeasonName season : growingSeasons) {
            seasons += season.toString() + ", ";
        }
        seasons = seasons.substring(0, seasons.length() - 2);
        return seasons;
    }

    @Override
    public String toString() {
        return String.format(
                "Name                 %s\n" +
                "Source               %s\n" +
                "Stages               %s\n" +
                "Total Harvest Time   %d\n" +
                "Fruit                %s\n" +
                "Fruit Harvest Cycle  %d\n" +
                "Base Sell Price      %d$\n" +
                "Is Fruit Edible      %s\n" +
                "Fruit Energy         %d\n" +
                "Season               %s\n",
                name, source, getStringStages(), totalGrowthDays, source, fruitHarvestCycle,
                fruitBaseSellPrice, isFruitEdible, fruitEnergy, getStringSeasons()
        );
    }
}

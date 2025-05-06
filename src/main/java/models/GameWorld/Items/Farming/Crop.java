package models.GameWorld.Items.Farming;

import models.GameWorld.Enums.SeasonName;

import java.util.ArrayList;
import java.util.Set;

/**
 * The Crop class is used to make different types of crops from JSON file,
 * so you shouldn't instantiate an object everytime you need to add a crop to the game.
 * Instead, you may use PlantedCrop to instantiate a crop object.
 */
public record Crop(String name, String source, ArrayList<Integer> growthStages,
                   int totalGrowthDays, boolean regrowable, int regrowDays,
                   int baseSellPrice, boolean isEdible, int energyRestored,
                   Set<SeasonName> growingSeasons, boolean canBecomeGiant, double spawningChance) {

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
                        "One Time             %s\n" +
                        "Regrowth Time        %d\n" +
                        "Base Sell Price      %d$\n" +
                        "Is Edible            %s\n" +
                        "Base Energy          %d\n" +
                        "Season               %s\n" +
                        "Can Become Giant     %s",
                name, source, getStringStages(), totalGrowthDays, !regrowable, regrowDays,
                baseSellPrice, isEdible, energyRestored, getStringSeasons(), canBecomeGiant
        );
    }
}

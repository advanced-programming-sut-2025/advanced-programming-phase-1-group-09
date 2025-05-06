package models.GameWorld.Items.Farming;

import models.GameWorld.Enums.SeasonName;

import java.util.ArrayList;
import java.util.Set;

public class Crop {
    private final String name;
    private final String source;
    private final ArrayList<Integer> growthStages;
    private final int totalGrowthDays;
    private final boolean regrowable;
    private final int regrowDays;
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energyRestored;
    private final Set<SeasonName> growingSeasons;
    private final boolean canBecomeGiant;

    public Crop(String name, String source, ArrayList<Integer> growthStages,
                int totalGrowthDays, boolean regrowable, int regrowDays,
                int baseSellPrice, boolean isEdible, int energyRestored,
                Set<SeasonName> growingSeasons, boolean canBecomeGiant) {
        this.name = name;
        this.source = source;
        this.growthStages = growthStages;
        this.totalGrowthDays = totalGrowthDays;
        this.regrowable = regrowable;
        this.regrowDays = regrowDays;
        this.baseSellPrice = baseSellPrice;
        this.isEdible = isEdible;
        this.energyRestored = energyRestored;
        this.growingSeasons = growingSeasons;
        this.canBecomeGiant = canBecomeGiant;
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

    public boolean isRegrowable() {
        return regrowable;
    }

    public int getRegrowDays() {
        return regrowDays;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public int getEnergyRestored() {
        return energyRestored;
    }

    public Set<SeasonName> getGrowingSeasons() {
        return growingSeasons;
    }

    public boolean canBecomeGiant() {
        return canBecomeGiant;
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

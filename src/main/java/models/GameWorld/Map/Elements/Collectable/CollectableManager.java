package models.GameWorld.Map.Elements.Collectable;

import models.DataManagers.CropMetaData;
import models.DataManagers.ForagingCropMetaData;
import models.DataManagers.TreeMetaData;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Farming.*;
import models.GameWorld.Items.Minerals.Stone;

import java.util.*;

public class CollectableManager {
    private static final Set<CollectableWrapper> collectables = new HashSet<>();
    private static double totalWeight = 0.0;
    private static final Map<SeasonName, List<CollectableWrapper>> seasonalDefinitions = new HashMap<>();
    private static final Map<SeasonName, Double> seasonalWeights = new HashMap<>();
    private static final Random random = new Random();

    static {
        for (SeasonName season : SeasonName.values()) {
            // Initialize seasonal definitions and weights
            seasonalDefinitions.put(season, new ArrayList<>());
            seasonalWeights.put(season, 0.0);

            // Crop Seeds
            for (Seed seed : CropMetaData.getAllSeeds()) {
                Crop crop = CropMetaData.getCrop(seed);
                if (crop.growingSeasons().contains(season)) {
                    register(new CollectableWrapper(
                            seed.getSpawningChance(),
                            () -> seed.newSeed(1)
                    ), season);
                }
            }

            // Tree Seeds
            for (Seed seed : TreeMetaData.getAllSeeds()) {
                Tree tree = TreeMetaData.getTree(seed);
                if (tree.getGrowingSeasons().contains(season)) {
                    register(new CollectableWrapper(
                            seed.getSpawningChance(),
                            () -> seed.newSeed(1)
                    ), season);
                }
            }

            // Foraging Crops
            for (ForagingCrop crop : ForagingCropMetaData.getForagingCrops()) {
                if (crop.getSeasons().contains(season)) {
                    register(new CollectableWrapper(
                            crop.getSpawningChance(),
                            crop::clone
                    ), season);
                }
            }

            // Wood
            register(new CollectableWrapper(0.3, () -> new Wood(3)), season);

            // Stone
            register(new CollectableWrapper(0.3, () -> new Stone(3)), season);
        }
    }

    private static void register(CollectableWrapper def, SeasonName season) {
        collectables.add(def);
        totalWeight += def.chance();

        seasonalDefinitions.get(season).add(def);
        seasonalWeights.put(season,
                seasonalWeights.get(season) + def.chance()
        );
    }

    public static List<CollectableWrapper> getAll() {
        return collectables.stream().toList();
    }

    public static double getTotalWeight() {
        return totalWeight;
    }

    public static List<CollectableWrapper> getSeasonal(SeasonName season) {
        return seasonalDefinitions.get(season);
    }

    public static double getSeasonalWeight(SeasonName season) {
        return seasonalWeights.get(season);
    }

    public static Collectable getRandom(SeasonName season) {
        List<CollectableWrapper> pool = getSeasonal(season);
        double total = getSeasonalWeight(season);
        double r = random.nextDouble() * total;
        for (CollectableWrapper wrapper : pool) {
            r -= wrapper.chance();
            if (r <= 0) return wrapper.itemSupplier().get();
        }
        return pool.get(0).itemSupplier().get();
    }
}

package models.GameWorld.Map.Elements.Collectable;

import models.DataManagers.CropMetaData;
import models.DataManagers.ForagingCropMetaData;
import models.DataManagers.MineralMetaData;
import models.DataManagers.TreeMetaData;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Farming.*;
import models.GameWorld.Minerals.UnextractedMineral;

import java.util.*;

public class CollectableHolder {
    private static final Map<SeasonName, List<CollectableWrapper>> seasonalDefinitions = new HashMap<>();
    private static final Map<SeasonName, Double> seasonalWeights = new HashMap<>();

    private static final Map<SeasonName, List<CollectableWrapper>> seasonalSeeds = new HashMap<>();
    private static final Map<SeasonName, Double> seasonalSeedWeights = new HashMap<>();

    private static final Map<SeasonName, List<CollectableWrapper>> seasonalForagingCrop = new HashMap<>();
    private static final Map<SeasonName, Double> seasonalForagingCropWeights = new HashMap<>();

    private static final Random random = new Random();

    static {
        for (SeasonName season : SeasonName.values()) {
            // Initialize seasonal definitions and weights
            seasonalDefinitions.put(season, new ArrayList<>());
            seasonalWeights.put(season, 0.0);

            // Initialize seasonal seeds and weights
            seasonalSeeds.put(season, new ArrayList<>());
            seasonalSeedWeights.put(season, 0.0);

            // Initialize seasonal foraging crops and weights
            seasonalForagingCrop.put(season, new ArrayList<>());
            seasonalForagingCropWeights.put(season, 0.0);


            // Crop Seeds
            for (Seed seed : CropMetaData.getAllSeeds()) {
                CropDefinition cropDefinition = CropMetaData.getCrop(seed);
                if (cropDefinition.growingSeasons().contains(season)) {
                    CollectableWrapper cw = new CollectableWrapper(seed.getSpawningChance(), seed::clone);
                    register(cw, season);
                    registerSeed(cw, season);
                }
            }

            // Tree Seeds
            for (Seed seed : TreeMetaData.getAllSeeds()) {
                TreeDefinition treeDefinition = TreeMetaData.getTree(seed);
                if (treeDefinition.getGrowingSeasons().contains(season)) {
                    CollectableWrapper cw = new CollectableWrapper(seed.getSpawningChance(), seed::clone);
                    register(cw, season);
                    registerSeed(cw, season);
                }
            }

            // Foraging Crops
            for (ForagingCrop crop : ForagingCropMetaData.getForagingCrops()) {
                if (crop.getSeasons().contains(season)) {
                    CollectableWrapper cw = new CollectableWrapper(crop.getSpawningChance(), crop::clone);
                    register(cw, season);
                    registerForagingCrop(cw, season);
                }
            }
        }
    }

    private static void register(CollectableWrapper def, SeasonName season) {
        seasonalDefinitions.get(season).add(def);
        seasonalWeights.put(season,
                seasonalWeights.get(season) + def.chance()
        );
    }

    private static void registerSeed(CollectableWrapper def, SeasonName season) {
        seasonalSeeds.get(season).add(def);
        seasonalSeedWeights.put(season,
                seasonalSeedWeights.get(season) + def.chance()
        );
    }

    private static void registerForagingCrop(CollectableWrapper def, SeasonName season) {
        seasonalForagingCrop.get(season).add(def);
        seasonalForagingCropWeights.put(season,
                seasonalForagingCropWeights.get(season) + def.chance()
        );
    }

    public static Collectable getRandomCollectable(SeasonName season) {
        List<CollectableWrapper> pool = seasonalDefinitions.get(season);
        double total = seasonalWeights.get(season);
        double r = random.nextDouble() * total;
        for (CollectableWrapper wrapper : pool) {
            r -= wrapper.chance();
            if (r <= 0) return wrapper.itemSupplier().get();
        }
        return pool.getFirst().itemSupplier().get();
    }

    public static Seed getRandomSeed(SeasonName season) {
        List<CollectableWrapper> pool = seasonalSeeds.get(season);
        double total = seasonalSeedWeights.get(season);
        double r = random.nextDouble() * total;
        for (CollectableWrapper wrapper : pool) {
            r -= wrapper.chance();
            if (r <= 0) return (Seed) wrapper.itemSupplier().get();
        }
        return (Seed) pool.getFirst().itemSupplier().get();
    }

    public static ForagingCrop getRandomForagingCrop(SeasonName season) {
        List<CollectableWrapper> pool = seasonalForagingCrop.get(season);
        double total = seasonalForagingCropWeights.get(season);
        double r = random.nextDouble() * total;
        for (CollectableWrapper wrapper : pool) {
            r -= wrapper.chance();
            if (r <= 0) return (ForagingCrop) wrapper.itemSupplier().get();
        }
        return (ForagingCrop) pool.getFirst().itemSupplier().get();
    }
}

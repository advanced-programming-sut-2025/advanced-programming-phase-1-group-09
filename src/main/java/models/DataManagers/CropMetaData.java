package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Farming.Crop;
import models.GameWorld.Farming.Seed;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CropMetaData {
    private static final Map<String, Crop> crops = new HashMap<>();
    private static final Map<String, Seed> seeds = new HashMap<>();
    private static final Map<Seed, Crop> seedToCrop = new HashMap<>();

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Crop.class, new CropDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = CropMetaData.class.getClassLoader().getResourceAsStream("JSON/crops.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<Crop> cropList = mapper.readValue(inputStream, new TypeReference<>() {});

            for (Crop crop : cropList) {
                crops.put(crop.name(), crop);
                Seed seed = new Seed(crop.source(), 10, crop.spawningChance());
                seeds.put(seed.getName(), seed);
                seedToCrop.put(seed, crop);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load crops", e);
        }
    }

    public static Crop getCrop(String name) {
        return crops.get(name);
    }

    public static Crop getCrop(Seed seed) {
        return seedToCrop.get(seed);
    }

    public static Seed getSeed(String seedName) {
        return seeds.get(seedName);
    }

    public static Collection<Seed> getAllSeeds() {
        return seeds.values();
    }

    public static Collection<Seed> getSelectedSeeds(SeasonName season) {
        return seedToCrop.entrySet().stream()
                .filter(entry -> {
                    Crop crop = entry.getValue();
                    return crop.growingSeasons().contains(season);
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static Collection<Crop> getAllCrops() {
        return crops.values();
    }

    public static Collection<Crop> getSelectedCrops(SeasonName season) {
        return crops.values().stream()
                .filter(crop -> crop.growingSeasons().contains(season))
                .collect(Collectors.toList());
    }
}

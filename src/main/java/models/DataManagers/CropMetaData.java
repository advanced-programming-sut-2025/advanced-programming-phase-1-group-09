package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Items.Farming.Crop;
import models.GameWorld.Items.Farming.Seed;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                crops.put(crop.getName(), crop);
                if (crop.getSource().contains("Seed")) {
                    Seed seed = new Seed(crop.getSource(), 10);
                    seeds.put(seed.getName(), seed);
                    seedToCrop.put(seed, crop);
                }
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

    public static Collection<Crop> getAllCrops() {
        return crops.values();
    }
}

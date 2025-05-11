package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Entity.Animals.BarnAnimals.BarnAnimal;
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

public class BarnAnimalMetaData {
    private static final Map<String, BarnAnimal> barnAnimals = new HashMap<>();
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(BarnAnimal.class, new BarnAnimalDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = BarnAnimalMetaData.class.getClassLoader().getResourceAsStream("JSON/barn_animals.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<BarnAnimal> barnAnimalList = mapper.readValue(inputStream, new TypeReference<>() {});

            for (BarnAnimal barnAnimal : barnAnimalList) {
                barnAnimals.put(barnAnimal.name(), barnAnimal);
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

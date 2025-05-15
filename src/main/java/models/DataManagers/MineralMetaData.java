package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Map.Elements.Collectable.CollectableWrapper;
import models.GameWorld.Minerals.UnextractedMineral;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MineralMetaData {
    private static final HashMap<String, UnextractedMineral> minerals = new HashMap<>();
    private static double totalSpawningChances = 0.0;
    private final static Random random = new Random();

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(UnextractedMineral.class, new MineralDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = MineralMetaData.class.getClassLoader()
                    .getResourceAsStream("JSON/minerals.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<UnextractedMineral> mineralList = mapper.readValue(inputStream, new TypeReference<>() {});

            for (UnextractedMineral mineral : mineralList) {
                minerals.put(mineral.getName(), mineral);
                totalSpawningChances += mineral.getSpawningChance();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load minerals", e);
        }
    }

    public static UnextractedMineral getMineral(String name) {
        return minerals.get(name).clone();
    }

    public static Collection<UnextractedMineral> getAllMinerals() {
        return minerals.values();
    }

    public static UnextractedMineral getRandomMineral() {
        List<UnextractedMineral> pool = minerals.values().stream().toList();
        double r = random.nextDouble() * totalSpawningChances;
        for (UnextractedMineral mineral : pool) {
            r -= mineral.getSpawningChance();
            if (r <= 0) return mineral.clone();
        }
        return pool.getFirst().clone();
    }
}

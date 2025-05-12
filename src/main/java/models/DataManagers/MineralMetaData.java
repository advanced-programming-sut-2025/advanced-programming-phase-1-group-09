package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Minerals.UnextractedMineral;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MineralMetaData {
    private static final HashMap<String, UnextractedMineral> minerals = new HashMap<>();

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
}

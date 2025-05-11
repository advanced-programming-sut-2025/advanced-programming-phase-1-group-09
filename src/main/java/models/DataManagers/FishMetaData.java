package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Entity.Fish.Fish;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FishMetaData {
    private static final List<Fish> fish = new ArrayList<>();
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Fish.class, new FishDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = FishMetaData.class.getClassLoader().getResourceAsStream("JSON/fish.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<Fish> fishList = mapper.readValue(inputStream, new TypeReference<>() {});
            fish.addAll(fishList);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load fish", e);
        }
    }
}


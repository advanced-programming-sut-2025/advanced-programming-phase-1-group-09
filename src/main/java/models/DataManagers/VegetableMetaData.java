package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Farming.Vegetable;
import models.GameWorld.Items.Item;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class VegetableMetaData {
    private static final List<Item> vegetable = new ArrayList<>();
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Vegetable.class, new VegetableDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = VegetableMetaData.class.getClassLoader().getResourceAsStream("JSON/vegetable.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<Vegetable> vegetableList = mapper.readValue(inputStream, new TypeReference<>() {});
            vegetable.addAll(vegetableList);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load vegetable", e);
        }
    }

    public static List<Item> getVegetable() {
        return vegetable;
    }

}


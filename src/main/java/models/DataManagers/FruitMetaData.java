package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Farming.Fruit;
import models.GameWorld.Items.Item;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FruitMetaData {
    private static final List<Item> fruit = new ArrayList<>();
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Fruit.class, new FruitDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = FruitMetaData.class.getClassLoader().getResourceAsStream("JSON/fruit.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<Fruit> fruitList = mapper.readValue(inputStream, new TypeReference<>() {});
            fruit.addAll(fruitList);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load fruit", e);
        }
    }

    public static List<Item> getFruit() {
        return fruit;
    }

}


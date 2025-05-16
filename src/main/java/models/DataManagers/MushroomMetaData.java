package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Farming.Mushroom;
import models.GameWorld.Items.Item;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MushroomMetaData {
    private static final List<Item> mushroom = new ArrayList<>();
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Mushroom.class, new MushroomDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = MushroomMetaData.class.getClassLoader().getResourceAsStream("JSON/mushroom.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<Mushroom> mushroomList = mapper.readValue(inputStream, new TypeReference<>() {});
            mushroom.addAll(mushroomList);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load mushroom", e);
        }
    }

    public static List<Item> getMushroom() {
        return mushroom;
    }

}


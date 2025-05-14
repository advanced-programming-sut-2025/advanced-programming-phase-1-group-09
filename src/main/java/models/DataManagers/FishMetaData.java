package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Entity.Fish.CaughtFish;
import models.GameWorld.Entity.Fish.LiveFish;
import models.GameWorld.Items.Item;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FishMetaData {
    private static final List<LiveFish> fish = new ArrayList<>();
    private static final List<Item> caught = new ArrayList<>();
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(LiveFish.class, new FishDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = FishMetaData.class.getClassLoader().getResourceAsStream("JSON/fish.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<LiveFish> fishList = mapper.readValue(inputStream, new TypeReference<>() {});
            for(LiveFish fishElement : fishList){
                fish.add(fishElement);
                caught.add(new CaughtFish(fishElement.getName(),true,fishElement.getSellPrice()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load fish", e);
        }
    }

    public static List<LiveFish> getFish() {
        return fish;
    }

    public static List<Item> getCaught() { return caught; }
}


package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Entity.Animals.BarnAnimal;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class BarnAnimalMetaData {
    private static final List<BarnAnimal> barnAnimals = new ArrayList<>();
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
            barnAnimals.addAll(barnAnimalList);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load barn animals", e);
        }
    }

    public static List<BarnAnimal> getBarnAnimals() {
        return barnAnimals;
    }
}

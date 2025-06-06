package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Entity.Animals.CoopAnimal;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CoopAnimalMetaData {
    private static final List<CoopAnimal> coopAnimals = new ArrayList<>();
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(CoopAnimal.class, new CoopAnimalDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = CoopAnimalMetaData.class.getClassLoader().getResourceAsStream("JSON/coop_animals.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<CoopAnimal> coopAnimalList = mapper.readValue(inputStream, new TypeReference<>() {});
            coopAnimals.addAll(coopAnimalList);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load coop animals", e);
        }
    }

    public static List<CoopAnimal> getCoopAnimals() {
        return coopAnimals;
    }
}


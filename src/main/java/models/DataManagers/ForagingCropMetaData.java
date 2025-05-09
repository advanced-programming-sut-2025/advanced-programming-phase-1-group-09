package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Farming.ForagingCrop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForagingCropMetaData {
    private static final Map<String, ForagingCrop> foragingCrops = new HashMap<>();

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(ForagingCrop.class, new ForagingCropDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = CropMetaData.class.getClassLoader()
                    .getResourceAsStream("JSON/foraging_crops.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<ForagingCrop> foragingCropList = mapper.readValue(inputStream, new TypeReference<>() {});

            for (ForagingCrop crop : foragingCropList) {
                foragingCrops.put(crop.getName(), crop);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load crops", e);
        }
    }

    public static ForagingCrop getForagingCrop(String name) {
        return foragingCrops.get(name);
    }

    public static Collection<ForagingCrop> getForagingCrops() {
        return foragingCrops.values();
    }
}

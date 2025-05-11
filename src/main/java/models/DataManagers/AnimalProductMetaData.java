package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Items.AnimalProducts.AnimalProduct;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AnimalProductMetaData {
    private static final List<AnimalProduct> animalProducts = new ArrayList<>();
    static{
        try{
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(AnimalProduct.class, new AnimalProductDeserializer());
            mapper.registerModule(module);

            InputStream input = AnimalProductMetaData.class.getResourceAsStream("/animal_products.json");
            if (input == null) {
                throw new RuntimeException("animal_products.json not found in resources!");
            }

            List<AnimalProduct> elementList = mapper.readValue(input, new TypeReference<>() {});
            animalProducts.addAll(elementList);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load animal inedible product metadata", e);
        }
    }
}

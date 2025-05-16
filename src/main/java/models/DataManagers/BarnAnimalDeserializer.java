package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Entity.Animals.BarnAnimal;
import models.GameWorld.Items.AnimalProducts.AnimalProduct;

import java.io.IOException;
import java.util.ArrayList;

public class BarnAnimalDeserializer extends JsonDeserializer<BarnAnimal> {
    @Override
    public BarnAnimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        String name = node.get("name").asText();
        int price = node.get("price").asInt();
        String[] livingPlaces = node.get("livingPlaces").asText().split(",");
        ArrayList<AnimalProduct> products = new ArrayList<>();
        for(AnimalProduct product : AnimalProductMetaData.animalProducts ){
            if(product.getAnimal().getName().equals(name)){
                products.add(product);
            }
        }
        return new BarnAnimal(price, name, products, livingPlaces);
    }
}


package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Entity.Animals.Animal;
import models.GameWorld.Items.AnimalProducts.AnimalProduct;

import java.io.IOException;

public class AnimalProductDeserializer extends JsonDeserializer<AnimalProduct> {

    @Override
    public AnimalProduct deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("Name").asText();
        int sellPrice = node.get("Sell Price").asInt();
        Animal animal = DataHolder.getAnimal(node.get("Animal").asText());

        return new AnimalProduct(name,true,sellPrice,animal);
    }
}

package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Farming.Fruit;

import java.io.IOException;

public class FruitDeserializer extends JsonDeserializer<Fruit> {
    @Override
    public Fruit deserialize(JsonParser p, DeserializationContext ctxt) throws IOException{
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("name").asText();
        int sellPrice = node.get("base_sell_price").asInt();
        int gainedEnergy = node.get("energy").asInt();

        return new Fruit(name,gainedEnergy,sellPrice);
    }
}

package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Farming.Mushroom;

import java.io.IOException;

public class MushroomDeserializer extends JsonDeserializer<Mushroom> {
    @Override
    public Mushroom deserialize(JsonParser p, DeserializationContext ctxt) throws IOException{
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("name").asText();
        int sellPrice = node.get("base_sell_price").asInt();
        int gainedEnergy = node.get("energy").asInt();

        return new Mushroom(name,true,sellPrice,gainedEnergy);
    }
}

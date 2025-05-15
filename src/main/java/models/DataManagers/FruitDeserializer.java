package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Entity.Fish.LiveFish;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Items.Item;

import java.io.IOException;

public class FruitDeserializer extends JsonDeserializer<Item> {
    @Override
    public Item deserialize(JsonParser p, DeserializationContext ctxt) throws IOException{
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("name").asText();
        int sellPrice = node.get("base_sell_price").asInt();

        return new Item(name,true,sellPrice);
    }
}

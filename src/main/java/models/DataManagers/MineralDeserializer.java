package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Minerals.UnextractedMineral;

import java.io.IOException;

public class MineralDeserializer extends JsonDeserializer<UnextractedMineral> {
    @Override
    public UnextractedMineral deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        String name = node.get("name").asText();
        int price = node.get("price").asInt();
        double spawningChance = node.get("spawningChance").asDouble();
        int strength = node.get("strength").asInt();

        return new UnextractedMineral(name, price, spawningChance,"■", strength);
    }
}

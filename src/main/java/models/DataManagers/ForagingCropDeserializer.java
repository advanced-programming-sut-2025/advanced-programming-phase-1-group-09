package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Items.Farming.ForagingCrop;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ForagingCropDeserializer extends JsonDeserializer<ForagingCrop> {
    @Override
    public ForagingCrop deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        String name = node.get("name").asText();
        Set<SeasonName> growingSeasons = new HashSet<>();
        for (JsonNode seasonNode : node.get("growingSeasons")) {
            growingSeasons.add(SeasonName.valueOf(seasonNode.asText()));
        }
        int baseSellPrice = node.get("baseSellPrice").asInt();
        int energyRestored = node.get("energyRestored").asInt();
        double spawningChance = node.get("spawningChance").asDouble();

        return new ForagingCrop(name, baseSellPrice, energyRestored,growingSeasons, spawningChance);
    }
}

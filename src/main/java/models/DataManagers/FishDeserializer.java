package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Entity.Fish.LiveFish;
import models.GameWorld.Enums.SeasonName;

import java.io.IOException;

public class FishDeserializer extends JsonDeserializer<LiveFish> {
    @Override
    public LiveFish deserialize(JsonParser p, DeserializationContext ctxt) throws IOException{
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("Name").asText();
        int sellPrice = node.get("SellPrice").asInt();
        SeasonName season = null;
        switch (node.get("Season").asText()) {
            case "Spring" -> season = SeasonName.SPRING;
            case "Summer" -> season = SeasonName.SUMMER;
            case "Fall" -> season = SeasonName.FALL;
            case "Winter" -> season = SeasonName.WINTER;
        }
        boolean isMaximumSkillNeeded = node.get("Max Skill").asBoolean();
        return new LiveFish(sellPrice,season,name,isMaximumSkillNeeded);
    }
}

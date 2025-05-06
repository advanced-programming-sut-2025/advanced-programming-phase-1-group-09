package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Items.Farming.Crop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CropDeserializer extends JsonDeserializer<Crop> {
    @Override
    public Crop deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        String name = node.get("name").asText();
        String source = node.get("source").asText();

        String[] stageStrings = node.get("growthStages").asText().split("-");
        ArrayList<Integer> growthStages = new ArrayList<>();
        for (String s : stageStrings) {
            growthStages.add(Integer.parseInt(s));
        }

        int totalGrowthDays = node.get("totalGrowthDays").asInt();
        boolean regrowable = node.get("regrowable").asBoolean();
        int regrowDays = node.get("regrowDays").asInt();
        int baseSellPrice = node.get("baseSellPrice").asInt();
        boolean isEdible = node.get("isEdible").asBoolean();
        int energyRestored = node.get("energyRestored").asInt();

        Set<SeasonName> growingSeasons = new HashSet<>();
        for (JsonNode seasonNode : node.get("growingSeasons")) {
            growingSeasons.add(SeasonName.valueOf(seasonNode.asText()));
        }

        boolean canBecomeGiant = node.get("canBecomeGiant").asBoolean();

        return new Crop(name, source, growthStages, totalGrowthDays, regrowable, regrowDays,
                baseSellPrice, isEdible, energyRestored, growingSeasons, canBecomeGiant);
    }
}


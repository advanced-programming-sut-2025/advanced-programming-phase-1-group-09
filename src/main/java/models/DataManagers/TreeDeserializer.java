package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Items.Farming.Tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TreeDeserializer extends JsonDeserializer<Tree> {
    @Override
    public Tree deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        String name = node.get("name").asText();
        String source = node.get("source").asText();

        String[] stageStrings = node.get("growthStages").asText().split("-");
        ArrayList<Integer> growthStages = new ArrayList<>();
        for (String s : stageStrings) {
            growthStages.add(Integer.parseInt(s));
        }

        int totalGrowthDays = node.get("totalGrowthDays").asInt();
        String fruitName = node.get("fruit").asText();
        int fruitHarvestCycle = node.get("fruitHarvestCycle").asInt();
        int fruitBaseSellPrice = node.get("fruitBaseSellPrice").asInt();
        boolean isFruitEdible = node.get("isFruitEdible").asBoolean();
        int fruitEnergy = node.get("fruitEnergy").asInt();

        Set<SeasonName> growingSeasons = new HashSet<>();
        for (JsonNode seasonNode : node.get("growingSeasons")) {
            growingSeasons.add(SeasonName.valueOf(seasonNode.asText()));
        }

        double spawningChance = node.get("spawningChance").asDouble();

        return new Tree(
                name, source, growthStages, totalGrowthDays,
                fruitName, fruitHarvestCycle, fruitBaseSellPrice, isFruitEdible,
                fruitEnergy, growingSeasons, spawningChance
        );
    }
}

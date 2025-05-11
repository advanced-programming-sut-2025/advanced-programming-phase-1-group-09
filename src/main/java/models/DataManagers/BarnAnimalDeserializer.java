package models.DataManagers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import models.GameWorld.Entity.Animals.BarnAnimals.BarnAnimal;
import models.GameWorld.Enums.BarnAnimals;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Farming.Crop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BarnAnimalDeserializer extends JsonDeserializer<BarnAnimal> {
    @Override
    public BarnAnimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        String name = node.get("name").asText();
        String source = node.get("price").asInt();

        String[] stageStrings = node.get("growthStages").asText().split("-");
        ArrayList<Integer> growthStages = new ArrayList<>();
        for (String s : stageStrings) {
            growthStages.add(Integer.parseInt(s));
        }

        int totalGrowthDays = node.get("totalGrowthDays").asInt();
        boolean oneTime = node.get("oneTime").asBoolean();
        int regrowDays = node.get("regrowDays").asInt();
        int baseSellPrice = node.get("baseSellPrice").asInt();
        boolean isEdible = node.get("isEdible").asBoolean();
        int baseEnergy = node.get("baseEnergy").asInt();
        int baseHealth = node.get("baseHealth").asInt();

        Set<SeasonName> growingSeasons = new HashSet<>();
        for (JsonNode seasonNode : node.get("growingSeasons")) {
            growingSeasons.add(SeasonName.valueOf(seasonNode.asText()));
        }

        boolean canBecomeGiant = node.get("canBecomeGiant").asBoolean();
        double spawningChance = node.get("spawningChance").asDouble();

        return new BarnAnimal();
    }
}


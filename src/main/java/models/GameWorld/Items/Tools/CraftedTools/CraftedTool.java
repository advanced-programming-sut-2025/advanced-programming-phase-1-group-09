package models.GameWorld.Items.Tools.CraftedTools;

import models.GameWorld.Items.Minerals.Mineral;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Skills.Skill;

import java.util.LinkedHashMap;

public class CraftedTool extends Tool {
    private final String description;
    private final Skill source;
    private final int sellPrice;
    private final LinkedHashMap<Mineral,Integer> ingredients;

    public CraftedTool(String description, Skill source, int sellPrice, LinkedHashMap<Mineral, Integer> ingredients) {
        this.description = description;
        this.source = source;
        this.sellPrice = sellPrice;
        this.ingredients = ingredients;
    }
}

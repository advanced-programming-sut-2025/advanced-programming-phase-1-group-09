package models.GameWorld.Items.Tools.PrimaryTools;

import models.GameWorld.Items.Tools.Tool;

import java.util.LinkedHashMap;

public class PrimaryTool extends Tool {
    private int buyPrice;
    private int currentConsumeOfEnergy;
    private String currentType;
    private final LinkedHashMap<String,Integer> types;

    public PrimaryTool(LinkedHashMap<String, Integer> types) {
        this.types = types;
    }
}

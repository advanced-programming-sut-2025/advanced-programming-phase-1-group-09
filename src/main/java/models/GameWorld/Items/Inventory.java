package models.GameWorld.Items;

import java.util.LinkedHashMap;

public class Inventory {
    private final LinkedHashMap<String,Integer> types;
    private int currentStorage;
    private String currentType;
}

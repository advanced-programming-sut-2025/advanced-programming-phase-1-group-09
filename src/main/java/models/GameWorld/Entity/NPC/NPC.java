package models.GameWorld.Entity.NPC;

import models.GameWorld.Entity.Entity;
import models.GameWorld.Items.Item;
import models.GameWorld.Map.Buildings.PrefabBuildings.ConstantBuildings.NPCsHouse.NPCsHouse;
import models.GameWorld.Quest;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class NPC implements Entity {
    private final String job;
    private final NPCsHouse house;
    private final ArrayList<Item> favorites;
    private final LinkedHashMap<String, Feelings> dialogs;
    private final ArrayList<Quest> quests;
}

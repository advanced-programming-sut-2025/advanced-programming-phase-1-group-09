package models.GameWorld.Entity.NPC;

import models.GameWorld.Entity.Entity;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;
import models.GameWorld.Quest;

import java.util.ArrayList;

public class NPC implements Entity {
    private final String job;
    //private final NPCsHouse house;
    private final ArrayList<Item> favorites;
    // private final LinkedHashMap<String, Feelings> dialogs;
    private final ArrayList<Quest> quests;

    public NPC(String job, ArrayList<Item> favorites, ArrayList<Quest> quests) {
        this.job = job;
        this.favorites = favorites;
        this.quests = quests;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void interact(Player player) {

    }
}

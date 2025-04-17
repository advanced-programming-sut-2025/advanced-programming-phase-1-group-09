package models.GameWorld.Entity.Player;

import models.GameWorld.Items.Inventory;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.TrashCan;

import java.util.LinkedHashMap;

public class PlayerInventory {
    private Inventory inventory;
    private TrashCan trashCan;
    private Item currentItem;
    private final LinkedHashMap<Item,Integer> items;
}

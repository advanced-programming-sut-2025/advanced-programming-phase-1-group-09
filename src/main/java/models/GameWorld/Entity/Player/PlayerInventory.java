package models.GameWorld.Entity.Player;

import models.GameWorld.Items.Miscellaneous.Backpack;
import models.GameWorld.Items.Miscellaneous.Inventory;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Miscellaneous.InventorySlot;
import models.GameWorld.Items.Miscellaneous.TrashCan;
import models.GameWorld.Items.Seeds.Seed;
import models.GameWorld.Items.Tools.PrimaryTools.*;

public class PlayerInventory {
    private Backpack backpack;
    private TrashCan trashCan;
    private final Inventory mainInventory;
    private Item currentItem;

    public PlayerInventory() {
        this.backpack = Backpack.PRIMARY;
        this.trashCan = TrashCan.PRIMARY;
        this.mainInventory = new Inventory(backpack.getCapacity());
        this.currentItem = new Axe();

        addItem(this.currentItem);
        addItem(new Hoe());
        addItem(new Pickaxe());
        addItem(new Scythe());
        addItem(new WateringCan());
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
        if (backpack == Backpack.DELUXE) mainInventory.setIsCapacityUnlimited(true);
        mainInventory.setCapacity(backpack.getCapacity());
    }

    public TrashCan getTrashCan() {
        return trashCan;
    }

    public void setTrashCan(TrashCan trashCan) {
        this.trashCan = trashCan;
    }

    public void addItem(Item item) {
        mainInventory.addItem(item);
    }

    public Inventory getMainInventory() {
        return mainInventory;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item item) {
        this.currentItem = item;
    }

    public Item findItem(String itemName) {
        for (InventorySlot slot : mainInventory.getSlots()) {
            if (slot.item().getName().equals(itemName)) return slot.item();
        }
        return null;
    }
}

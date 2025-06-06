package models.GameWorld.Items.Miscellaneous;

import models.GameWorld.Items.Item;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {
    private final ArrayList<InventorySlot> slots;
    private int capacity;
    private boolean isCapacityUnlimited;
    private int itemCount;

    public Inventory(int initialCapacity) {
        this.capacity = initialCapacity;
        this.slots = new ArrayList<>();
        this.isCapacityUnlimited = false;
        this.itemCount = 0;
    }

    public int getItemQuantity(Item item) {
        for (InventorySlot slot : slots) {
            if (slot.item().equals(item)) return slot.quantity();
        }
        return 0;
    }

    public boolean addItem(Item item, int quantity) {
        if (item.isStackable()) {
            for (InventorySlot slot : slots) {
                if (slot.isStackableWith(item)) {
                    slot.increaseQuantity(quantity);
                    return true;
                }
            }
        }

        if (isInventoryFull()) return false;

        slots.add(new InventorySlot(item, quantity));
        itemCount++;
        return true;
    }

    /**
     * @return the amount of the specific item that has been removed
     */
    public int reduceItemQuantity(Item item, int quantity) {
        for (InventorySlot slot : slots) {
            if (slot.item().getName().equals(item.getName())) {
                int beforeChangeQuantity = slot.quantity();
                slot.reduceQuantity(quantity);
                if (slot.quantity() == 0) removeItem(item);
                return Math.min(quantity, beforeChangeQuantity);
            }
        }
        return 0;
    }

    /**
     * @return the amount of the specific item that has been removed
     */
    public int removeItem(Item item) {
        Iterator<InventorySlot> iterator = slots.iterator();
        while (iterator.hasNext()) {
            InventorySlot slot = iterator.next();
            if (slot.item() == item && item.isStackable()) {
                int quantity = slot.quantity();
                itemCount--;
                iterator.remove();
                return quantity;
            }
        }
        return 0;
    }

    public ArrayList<InventorySlot> getSlots() {
        return slots;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setIsCapacityUnlimited(boolean condition) {
        this.isCapacityUnlimited = condition;
    }

    public int getItemCount() {
        return itemCount;
    }

    public boolean isInventoryFull() {
        return !isCapacityUnlimited && (itemCount >= capacity);
    }
}

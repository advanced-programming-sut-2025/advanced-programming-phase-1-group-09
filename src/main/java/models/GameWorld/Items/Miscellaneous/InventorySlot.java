package models.GameWorld.Items.Miscellaneous;

import models.GameWorld.Items.Item;
import models.GameWorld.Items.StackableItem;

public record InventorySlot(Item item) {
    public int getItemQuantity() {
        if (!item.isStackable()) return 1;
        return ((StackableItem) item).getQuantity();
    }

    public boolean isStackableWith(Item other) {
        return item.isStackable() && item.getName().equals(other.getName());
    }
}

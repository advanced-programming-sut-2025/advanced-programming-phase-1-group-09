package models.GameWorld.Farming;

import models.GameWorld.Items.Item;
import models.GameWorld.Items.StackableItem;
import models.GameWorld.Map.Elements.Collectable.Collectable;

public class Wood extends StackableItem implements Collectable {
    public Wood(int quantity) {
        super("Wood", quantity, 1);
    }

    @Override
    public Item collect() {
        return this;
    }
}

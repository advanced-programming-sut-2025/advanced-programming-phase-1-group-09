package models.GameWorld.Items.Minerals;

import models.GameWorld.Items.StackableItem;
import models.GameWorld.Map.Elements.Collectable.Collectable;

public class Stone extends StackableItem implements Collectable {
    public Stone(int quantity) {
        super("Stone", quantity, 1);
    }

    @Override
    public Stone collect() {
        return this;
    }
}

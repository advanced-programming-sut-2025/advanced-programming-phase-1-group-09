package models.GameWorld.Map.Elements.Collectable;

import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;
import models.GameWorld.Map.Elements.MapElement;

public interface Collectable extends MapElement {
    Item collect();

    @Override
    default boolean isInteractable() {
        return false;
    }

    @Override
    default void interact(Player player) {
        // Nothing to do here
    }
}

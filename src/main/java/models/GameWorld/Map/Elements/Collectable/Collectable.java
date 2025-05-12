package models.GameWorld.Map.Elements.Collectable;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;
import models.GameWorld.Map.Elements.MapElement;

import java.util.Random;

public interface Collectable extends MapElement {
    Random random = new Random();
    Item collect();

    default int getRandom() {
        return random.nextInt(4) + 1;
    }

    @Override
    default boolean isInteractable() {
        return true;
    }

    @Override
    default void interact(Player player, Coordinate position) {
        if (!player.getMainInventory().isInventoryFull()) {
            player.getMainInventory().addItem(collect(), getRandom());
            player.getFarm().getTile(position).removeElement(this);
        }
    }
}

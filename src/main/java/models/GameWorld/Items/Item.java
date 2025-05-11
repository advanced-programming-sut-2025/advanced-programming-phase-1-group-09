package models.GameWorld.Items;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;

public abstract class Item {
    protected final String name;
    protected final boolean isStackable;

    public Item(String name, boolean isStackable) {
        this.name = name;
        this.isStackable = isStackable;
    }

    public String getName() {
        return name;
    }

    public boolean isStackable() {
        return isStackable;
    }

    public abstract void use(Coordinate target, Player player, Game game);
}

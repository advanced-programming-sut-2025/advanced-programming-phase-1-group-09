package models.GameWorld.Items;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;

public abstract class Item {
    protected final String name;
    protected final boolean isStackable;
    protected final int price;

    public Item(String name, boolean isStackable, int price) {
        this.name = name;
        this.isStackable = isStackable;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public boolean isStackable() {
        return isStackable;
    }

    public int getPrice() {
        return price;
    }

    public abstract void use(Coordinate target, Player player, Game game);


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass().getSuperclass() != obj.getClass().getSuperclass()) return false;
        Item item = (Item) obj;
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

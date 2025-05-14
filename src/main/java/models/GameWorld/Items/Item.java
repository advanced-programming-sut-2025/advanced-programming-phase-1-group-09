package models.GameWorld.Items;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;

public abstract class Item implements Cloneable{
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

    @Override
    public Item clone() {
        try {
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should not happen if Cloneable is implemented//
        }
    }

    public abstract void use(Coordinate target, Player player, Game game);
}

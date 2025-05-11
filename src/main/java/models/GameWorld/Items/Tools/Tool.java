package models.GameWorld.Items.Tools;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;

public abstract class Tool extends Item {
    protected enum Type {PRIMARY, COPPER, SILVER, GOLD, IRIDIUM}

    protected int level;

    public Tool(String name) {
        super(name, false);
        this.level = 0;
    }

    public int getLevel() {
        return level;
    }

    public String getType() {
        return Type.values()[level].toString();
    }

    public void upgrade() {
        if (level < getMaxLevel()) level++;
    }

    protected abstract int getMaxLevel();

}

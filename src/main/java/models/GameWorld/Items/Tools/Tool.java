package models.GameWorld.Items.Tools;

import models.GameWorld.Enums.WeatherType;
import models.GameWorld.Items.Item;

public abstract class Tool extends Item {
    protected enum Type {PRIMARY, COPPER, SILVER, GOLD, IRIDIUM}

    protected int level;

    public Tool(String name, int price) {
        super(name, false, price);
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

    protected double energyCoefficient(WeatherType weather) {
        return switch (weather) {
            case RAINY, STORM -> 1.5;
            case SNOWY -> 2;
            default -> 1;
        };
    }
}

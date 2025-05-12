package models.GameWorld.Map.Elements.Buyable.Coops;

import models.GameWorld.Entity.Animals.CoopAnimal;
import models.GameWorld.Map.Elements.Buyable.BuyableMapElement;
import models.GameWorld.Map.Elements.MultiTileElement;

import java.util.ArrayList;

public abstract class CoopElement implements MultiTileElement, BuyableMapElement {
    private final int maxCapacity;
    private int capacity = 0;
    private final ArrayList<CoopAnimal> animals = null;

    public CoopElement(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<CoopAnimal> getAnimals() {
        return animals;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

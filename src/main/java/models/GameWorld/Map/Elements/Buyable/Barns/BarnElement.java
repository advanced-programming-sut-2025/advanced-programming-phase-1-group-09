package models.GameWorld.Map.Elements.Buyable.Barns;

import models.GameWorld.Entity.Animals.BarnAnimal;
import models.GameWorld.Map.Elements.Buyable.BuyableMapElement;
import models.GameWorld.Map.Elements.MultiTileElement;

import java.util.ArrayList;

public abstract class BarnElement implements MultiTileElement, BuyableMapElement {
    private int capacity = 0;
    private final int maxCapacity;
    private final ArrayList<BarnAnimal> animals = null;

    public BarnElement(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public ArrayList<BarnAnimal> getAnimals() {
        return animals;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

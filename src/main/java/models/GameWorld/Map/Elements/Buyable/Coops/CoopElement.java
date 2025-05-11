package models.GameWorld.Map.Elements.Buyable.Coops;

import models.GameWorld.Entity.Animals.CoopAnimals.CoopAnimal;
import models.GameWorld.Map.Elements.Buyable.BuyableMapElement;
import models.GameWorld.Map.Elements.MultiTileElement;

import java.util.ArrayList;

public abstract class CoopElement implements MultiTileElement, BuyableMapElement {
    private final int capacity;
    private final ArrayList<CoopAnimal> animals = null;

    public CoopElement(int capacity) {
        this.capacity = capacity;
    }
}

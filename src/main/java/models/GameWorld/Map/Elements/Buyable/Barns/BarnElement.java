package models.GameWorld.Map.Elements.Buyable.Barns;

import models.GameWorld.Entity.Animals.BarnAnimal;
import models.GameWorld.Map.Elements.Buyable.BuyableMapElement;
import models.GameWorld.Map.Elements.MultiTileElement;

import java.util.ArrayList;

public abstract class BarnElement implements MultiTileElement, BuyableMapElement {
    private final int capacity;
    private final ArrayList<BarnAnimal> animals = null;

    public BarnElement(int capacity) {
        this.capacity = capacity;
    }
}

package models.GameWorld.Map.Elements.Barns;

import models.GameWorld.Entity.Animals.BarnAnimals.BarnAnimal;
import models.GameWorld.Map.Elements.MultiTileElement;

import java.util.ArrayList;

public abstract class BarnElement implements MultiTileElement {
    private final int cost;
    private final int woodCost;
    private final int stoneCost;
    private final int size;
    private final ArrayList<BarnAnimal> animals = null;

    public BarnElement(int cost, int woodCost, int stoneCost, int size) {
        this.cost = cost;
        this.woodCost = woodCost;
        this.stoneCost = stoneCost;
        this.size = size;
    }
}

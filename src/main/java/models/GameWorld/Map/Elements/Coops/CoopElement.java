package models.GameWorld.Map.Elements.Coops;

import models.GameWorld.Entity.Animals.BarnAnimals.BarnAnimal;
import models.GameWorld.Map.Elements.MapElement;
import models.GameWorld.Map.Elements.MultiTileElement;

import java.util.ArrayList;

public abstract class CoopElement implements MultiTileElement {
    private final int cost;
    private final int woodCost;
    private final int stoneCost;
    private final int size;
    private final ArrayList<CoopAnimal> animals = null;

    public CoopElement(int cost, int woodCost, int stoneCost, int size) {
        this.cost = cost;
        this.woodCost = woodCost;
        this.stoneCost = stoneCost;
        this.size = size;
    }
}

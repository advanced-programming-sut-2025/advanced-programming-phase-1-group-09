package models.GameWorld.Map.Buildings.NewConstructedBuildings;

import models.GameWorld.Map.Buildings.Building;

public class NewConstructedBuilding extends Building {
    private final int moneyNeeded;

    public NewConstructedBuilding(int moneyNeeded, int width, int height) {
        super(width, height);
        this.moneyNeeded = moneyNeeded;
    }
}

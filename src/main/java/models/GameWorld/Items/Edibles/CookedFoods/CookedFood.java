package models.GameWorld.Items.Edibles.CookedFoods;

import models.GameWorld.Items.Edibles.Edible;

public class CookedFood extends Edible {
    public CookedFood(String name, int quantity, int gainedEnergy, int sellPrice) {
        super(name, quantity, gainedEnergy, sellPrice);
    }
}

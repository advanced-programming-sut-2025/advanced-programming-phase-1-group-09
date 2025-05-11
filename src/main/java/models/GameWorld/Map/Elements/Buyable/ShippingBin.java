package models.GameWorld.Map.Elements.Buyable;

import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.Elements.MapElement;

public class ShippingBin implements MapElement,BuyableMapElement {

    @Override
    public int getPrice() {
        return 250;
    }

    @Override
    public int getWoodCost() {
        return 150;
    }

    @Override
    public int getStoneCost() {
        return 0;
    }

    @Override
    public String getName() {
        return "Shipping Bin";
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void interact(Player player) {

    }
}

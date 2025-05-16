package models.GameWorld.Map.Elements.Buyable;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.Elements.MultiTileElement;

public class Well implements MultiTileElement,BuyableMapElement {

    private int y;
    private int x;

    @Override
    public int getPrice() {
        return 1000;
    }

    @Override
    public int getWoodCost() {
        return 0;
    }

    @Override
    public int getStoneCost() {
        return 75;
    }

    @Override
    public int getHeight() {
        return 3;
    }

    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public String getName() {
        return "Well";
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void interact(Player player, Coordinate position) {

    }
}

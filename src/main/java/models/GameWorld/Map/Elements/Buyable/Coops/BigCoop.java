package models.GameWorld.Map.Elements.Buyable.Coops;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;

public class BigCoop extends CoopElement {
    //private boolean isBought ???
    private int x;
    private int y;
    public BigCoop(int x,int y) {
        super(8);
        this.x = x;
        this.y = y;
    }

    @Override
    public int getHeight() {
        return 6;
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
        return "Big Coop";
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void interact(Player player, Coordinate position) {

    }

    @Override
    public int getPrice() {
        return 10000;
    }

    @Override
    public int getWoodCost() {
        return 400;
    }

    @Override
    public int getStoneCost() {
        return 150;
    }
}

package models.GameWorld.Map.Elements.Buyable.Coops;

import models.GameWorld.Entity.Player.Player;

public class DeluxeCoop extends CoopElement {
    //private boolean isBought ???
    private int x;
    private int y;
    public DeluxeCoop(int x,int y) {
        super(12);
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
        return "Deluxe Coop";
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public int getPrice() {
        return 20000;
    }

    @Override
    public int getWoodCost() {
        return 500;
    }

    @Override
    public int getStoneCost() {
        return 200;
    }
}

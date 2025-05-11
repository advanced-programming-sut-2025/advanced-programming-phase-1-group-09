package models.GameWorld.Map.Elements.Buyable.Barns;

import models.GameWorld.Entity.Player.Player;

public class Barn extends BarnElement{
    //private boolean isBought ???
    private int x;
    private int y;
    public Barn(int x,int y) {
        super(4);
        this.x = x;
        this.y = y;
    }

    @Override
    public int getHeight() {
        return 7;
    }

    @Override
    public int getWidth() {
        return 4;
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
        return "Barn";
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
        return 6000;
    }

    @Override
    public int getWoodCost() {
        return 350;
    }

    @Override
    public int getStoneCost() {
        return 150;
    }
}

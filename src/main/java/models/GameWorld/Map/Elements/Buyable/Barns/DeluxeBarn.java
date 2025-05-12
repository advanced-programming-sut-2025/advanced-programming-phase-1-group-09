package models.GameWorld.Map.Elements.Buyable.Barns;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;

public class DeluxeBarn extends BarnElement{
    //private boolean isBought ???
    private int x;
    private int y;
    public DeluxeBarn(int x,int y) {
        super(12);
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
        return "Deluxe Barn";
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
        return 25000;
    }

    @Override
    public int getWoodCost() {
        return 550;
    }

    @Override
    public int getStoneCost() {
        return 300;
    }
}

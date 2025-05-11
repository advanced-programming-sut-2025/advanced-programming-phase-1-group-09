package models.GameWorld.Map.Elements.Barns;

import models.GameWorld.Entity.Player.Player;

public class DeluxeBarn extends BarnElement{
    //private boolean isBought ???
    private int x;
    private int y;
    public DeluxeBarn(int x,int y) {
        super(25000,550,300,12);
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
        return false;
    }

    @Override
    public void interact(Player player) {

    }
}

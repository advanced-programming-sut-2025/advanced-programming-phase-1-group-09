package models.GameWorld.Map.Elements.Barns;

import models.GameWorld.Entity.Player.Player;

public class BigBarn extends BarnElement{
    //private boolean isBought ???
    private int x;
    private int y;
    public BigBarn(int x,int y) {
        super(12000,450,200,8);
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
        return "Big Barn";
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void interact(Player player) {

    }
}

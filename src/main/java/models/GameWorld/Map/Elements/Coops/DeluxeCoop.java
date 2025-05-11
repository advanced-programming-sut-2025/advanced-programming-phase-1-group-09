package models.GameWorld.Map.Elements.Coops;

import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.Elements.Barns.BarnElement;

public class DeluxeCoop extends CoopElement {
    //private boolean isBought ???
    private int x;
    private int y;
    public DeluxeCoop(int x,int y) {
        super(20000,500,200,4);
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
        return false;
    }

    @Override
    public void interact(Player player) {

    }
}

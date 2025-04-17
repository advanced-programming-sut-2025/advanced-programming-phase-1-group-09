package models.GameWorld.Entity.Fish;

import models.GameWorld.Season;

public class Fish {
    private final int sellPrice;
    private final Season season;
    public Fish(int sellPrice, Season season) {
        this.sellPrice = sellPrice;
        this.season = season;
    }
}

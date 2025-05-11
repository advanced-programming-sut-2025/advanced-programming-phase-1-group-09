package models.GameWorld.Entity.Fish;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Entity;
import models.GameWorld.Enums.SeasonName;

public class Fish implements Entity {
    private int x;
    private int y;
    private final int sellPrice;
    private final SeasonName season;
    private final String name;
    private final boolean isMaximumSkillNeeded;
    public Fish(int sellPrice, SeasonName season, String name, boolean isMaximumSkillNeeded) {
        this.sellPrice = sellPrice;
        this.season = season;
        this.name = name;
        this.isMaximumSkillNeeded = isMaximumSkillNeeded;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(y, x);
    }
}

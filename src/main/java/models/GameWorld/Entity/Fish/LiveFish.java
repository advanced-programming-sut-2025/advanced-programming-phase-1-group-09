package models.GameWorld.Entity.Fish;

import models.GameWorld.Entity.Entity;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Enums.SeasonName;

public class LiveFish implements Entity {
    private final int sellPrice;
    private final SeasonName season;
    private final String name;
    private final boolean isMaximumSkillNeeded;

    public LiveFish(int sellPrice,SeasonName season, String name, boolean isMaximumSkillNeeded) {
        this.sellPrice = sellPrice;
        this.season = season;
        this.name = name;
        this.isMaximumSkillNeeded = isMaximumSkillNeeded;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public SeasonName getSeason() {
        return season;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void interact(Player player) {

    }

    public boolean isMaximumSkillNeeded() {
        return isMaximumSkillNeeded;
    }

    @Override
    public LiveFish clone(){ return new LiveFish(sellPrice,season, name, isMaximumSkillNeeded); }
}

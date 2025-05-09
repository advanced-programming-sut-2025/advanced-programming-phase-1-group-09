package models.GameWorld.Farming;

import models.GameWorld.Map.Elements.MapElement;
import models.TimeObserver;

public abstract class Planted implements TimeObserver, MapElement {
    protected boolean wateredToday;
    protected int daysSinceLastStage;
    protected int currentStage;

    public Planted(boolean wateredToday, int daysSinceLastStage, int currentStage) {
        this.wateredToday = wateredToday;
        this.daysSinceLastStage = daysSinceLastStage;
        this.currentStage = currentStage;
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    public boolean isWateredToday() {
        return wateredToday;
    }

    public void setWateredToday(boolean wateredToday) {
        this.wateredToday = wateredToday;
    }

    public int getDaysSinceLastStage() {
        return daysSinceLastStage;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public abstract boolean isMature();
}

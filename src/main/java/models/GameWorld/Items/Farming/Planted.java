package models.GameWorld.Items.Farming;

import models.GameWorld.Coordinate;
import models.TimeObserver;

public abstract class Planted implements TimeObserver {
    protected final Coordinate position;
    protected boolean wateredToday;
    protected int daysSinceLastStage;
    protected int currentStage;

    public Planted(Coordinate position, boolean wateredToday, int daysSinceLastStage, int currentStage) {
        this.position = position;
        this.wateredToday = wateredToday;
        this.daysSinceLastStage = daysSinceLastStage;
        this.currentStage = currentStage;
    }

    public Coordinate getPosition() {
        return position;
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

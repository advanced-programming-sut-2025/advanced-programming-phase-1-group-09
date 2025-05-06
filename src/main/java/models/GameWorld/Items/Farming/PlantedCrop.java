package models.GameWorld.Items.Farming;

import models.GameWorld.Coordinate;
import models.GameWorld.TimeState;
import models.TimeObserver;

public class PlantedCrop implements TimeObserver {
    private final Crop cropDefinition;
    private final Coordinate position;
    private boolean wateredToday;
    private int daysSinceLastStage;
    private int currentStage;

    public PlantedCrop(Crop cropDefinition, Coordinate position) {
        this.cropDefinition = cropDefinition;
        this.position = position;
        this.wateredToday = false;
        this.daysSinceLastStage = 0;
        this.currentStage = 0;
    }

    @Override
    public void onTimeChange(TimeState newState) {
        if (!isMature() && wateredToday) {
            daysSinceLastStage++;
            if (daysSinceLastStage >= cropDefinition.getGrowthStages().get(currentStage)) {
                currentStage++;
                daysSinceLastStage = 0;
            }
        }
        // TODO
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

    private boolean isMature() {
        return currentStage >= cropDefinition.getGrowthStages().size();
    }
}

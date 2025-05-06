package models.GameWorld.Items.Farming;

import models.GameWorld.Coordinate;
import models.GameWorld.TimeState;

public class PlantedCrop extends Planted {
    private final Crop cropDefinition;

    public PlantedCrop(Crop cropDefinition, Coordinate position) {
        super(position, false, 0, 0);
        this.cropDefinition = cropDefinition;
    }

    public Crop getCropDefinition() {
        return cropDefinition;
    }

    @Override
    public void onTimeChange(TimeState newState) {
        if (!isMature() && wateredToday) {
            daysSinceLastStage++;
            if (daysSinceLastStage >= cropDefinition.growthStages().get(currentStage)) {
                currentStage++;
                daysSinceLastStage = 0;
            }
        }
        // TODO
    }

    @Override
    public boolean isMature() {
        return currentStage >= cropDefinition.growthStages().size();
    }
}

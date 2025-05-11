package models.GameWorld.Farming;

import models.GameWorld.Entity.Player.Player;
import models.GameWorld.TimeState;
import views.ConsoleColors;

public class PlantedCrop extends Planted {
    private final Crop cropDefinition;

    public PlantedCrop(Crop cropDefinition) {
        super(false, 0, 0);
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

    @Override
    public String getName() {
        return cropDefinition.name();
    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public String getSymbol() {
        return ConsoleColors.CYAN_BACKGROUND + "C";
    }
}

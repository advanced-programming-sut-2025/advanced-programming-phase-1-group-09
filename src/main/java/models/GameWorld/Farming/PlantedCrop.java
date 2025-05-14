package models.GameWorld.Farming;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Enums.WeatherType;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.TimeState;
import views.ConsoleColors;

public class PlantedCrop extends Planted {
    private final CropDefinition cropDefinition;

    public PlantedCrop(CropDefinition cropDefinition) {
        super(false, 0, 0);
        this.cropDefinition = cropDefinition;
    }

    public CropDefinition getCropDefinition() {
        return cropDefinition;
    }

    @Override
    public void onTimeChange(TimeState newState) {
        // The previous day
        if (!isMature() && wateredToday) {
            daysSinceLastStage++;
            if (daysSinceLastStage >= cropDefinition.growthStages().get(currentStage)) {
                currentStage++;
                daysSinceLastStage = 0;
            }
        }

        // The next day
        if (newState.getWeather().getCurrentWeather() == WeatherType.RAINY
         || newState.getWeather().getCurrentWeather() == WeatherType.STORM) {
            wateredToday = true;
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
    public void interact(Player player, Coordinate position) {
        Tool tool = player.getInventory().getCurrentTool();
        if (tool.getName().equals("Scythe")) {
            if (isMature()) {
                player.getField().getTile(position).removeElement(this);
                player.getField().getTile(position).addElement(new ItemCrop(cropDefinition));
            }
        }
    }

    @Override
    public String getSymbol() {
        return ConsoleColors.CYAN_BACKGROUND + "C";
    }

    @Override
    public String toString() {
        return String.format(
                "Crop: %s\n" +
                "Is Watered Today: %s\n" +
                "Current Stage: %d\n" +
                "Is Plant Mature: %s",
                cropDefinition.name(),
                wateredToday,
                currentStage + 1,
                isMature()
        );
    }
}

package models.GameWorld.Items.Farming;

import models.GameWorld.Coordinate;
import models.GameWorld.TimeState;

public class PlantedTree extends Planted {
    private final Tree treeDefinition;

    public PlantedTree(Tree treeDefinition, Coordinate position) {
        super(position, false, 0, 0);
        this.treeDefinition = treeDefinition;
    }

    public Tree getTreeDefinition() {
        return treeDefinition;
    }

    @Override
    public void onTimeChange(TimeState newState) {
        if (!isMature() && wateredToday) {
            daysSinceLastStage++;
            if (daysSinceLastStage >= treeDefinition.getGrowthStages().get(currentStage)) {
                currentStage++;
                daysSinceLastStage = 0;
            }
        }
        // TODO
    }

    @Override
    public boolean isMature() {
        return currentStage >= treeDefinition.getGrowthStages().size();
    }
}

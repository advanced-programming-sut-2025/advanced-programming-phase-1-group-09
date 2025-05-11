package models.GameWorld.Farming;

import models.GameWorld.Entity.Player.Player;
import models.GameWorld.TimeState;
import views.ConsoleColors;

public class PlantedTree extends Planted {
    private final Tree treeDefinition;

    public PlantedTree(Tree treeDefinition) {
        super(false, 0, 0);
        this.treeDefinition = treeDefinition;
    }

    public Tree getTreeDefinition() {
        return treeDefinition;
    }

    public static PlantedTree getMatureTree(Tree treeDefinition) {
        PlantedTree tree = new PlantedTree(treeDefinition);
        tree.currentStage = tree.treeDefinition.getGrowthStages().size();
        return tree;
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

    @Override
    public String getName() {
        return treeDefinition.getName();
    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public String getSymbol() {
        return ConsoleColors.GREEN_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BOLD_BRIGHT + "T";
    }
}

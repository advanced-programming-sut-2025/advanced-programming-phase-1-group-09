package models.GameWorld.Farming;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Map.Tile;
import models.GameWorld.TimeState;
import views.ConsoleColors;

public class PlantedTree extends Planted {
    private final TreeDefinition treeDefinition;
    private double damaged;
    private final int strength;

    public PlantedTree(TreeDefinition treeDefinition) {
        super(false, 0, 0);
        this.treeDefinition = treeDefinition;
        this.damaged = 0;
        this.strength = 10;
    }

    public TreeDefinition getTreeDefinition() {
        return treeDefinition;
    }

    public static PlantedTree getMatureTree(TreeDefinition treeDefinition) {
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
    public void interact(Player player, Coordinate position) {
        Tile tile = player.getFarm().getTile(position);
        Tool currentTool = player.getInventory().getCurrentTool();

        if (currentTool.getName().equals("Axe")) {
            damaged += (1 + 0.3 * player.getSkills().getForagingSkill().getLevel()) *
                    (1 + 0.4 * currentTool.getLevel());

            if (damaged >= strength) {
                tile.removeElement(this);
                tile.addElement(new Wood());
            }
        } else if (currentTool.getName().equals("Scythe")) {
            if (isMature() && treeDefinition.isFruitEdible()) {
                tile.addElement(treeDefinition.getFruit().clone());
            }
        }
    }

    @Override
    public String getSymbol() {
        return ConsoleColors.GREEN_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BOLD_BRIGHT + "T";
    }

    @Override
    public String toString() {
        return String.format(
                "Tree: %s\n" +
                "Is Watered Today: %s\n" +
                "Current Stage: %d\n" +
                "Is Plant Mature: %s",
                treeDefinition.getName(),
                wateredToday,
                currentStage + 1,
                isMature()
        );
    }
}

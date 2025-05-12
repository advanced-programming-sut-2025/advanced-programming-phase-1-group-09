package models.GameWorld.Items.Tools.PrimaryTools;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Farming.Planted;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Map.Elements.MapElement;
import models.GameWorld.Map.TerrainType;
import models.GameWorld.Map.Tile;

public class WateringCan extends Tool {
    private final int[] capacities = {40, 55, 70, 85, 100};
    private int waterLevel;

    public WateringCan() {
        super("WateringCan", 0);
        this.waterLevel = capacities[level];
    }

    @Override
    protected int getMaxLevel() {
        return 4;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {
        int energyConsumed = 5 - level;

        Tile tile = player.getFarm().getTile(target);
        if (tile.getTerrainType() == TerrainType.WATER) {
            waterLevel = capacities[level];
        } else if (waterLevel > 0) {
            for (MapElement element : tile.getElements()) {
                if (element instanceof Planted planted) {
                    planted.setWateredToday(true);
                }
            }
        }

        if (player.getSkills().getFarmingSkill().isMaxLevel()) energyConsumed--;
        player.changeEnergy(-Math.max(energyConsumed, 0));
    }
}

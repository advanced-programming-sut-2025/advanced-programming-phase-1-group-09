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

    public int getWaterLevel() {
        return waterLevel;
    }

    @Override
    protected int getMaxLevel() {
        return 4;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {
        int energyConsumed = 5 - level;

        Tile tile = player.getFarm().getTile(target);
        if (tile.getTerrainType() == TerrainType.LAKE) {
            waterLevel = capacities[level];
        } else if (waterLevel > 0) {
            for (MapElement element : tile.getElements()) {
                if (element instanceof Planted planted) {
                    planted.setWateredToday(true);
                }
            }
        }

        if (player.getSkills().getFarmingSkill().isMaxLevel()) energyConsumed--;
        int finalEnergyConsumption = (int)(
                Math.max(energyConsumed, 0) *
                energyCoefficient(game.getWeather().getCurrentWeather())
        );
        player.changeEnergy(-finalEnergyConsumption);
    }
}

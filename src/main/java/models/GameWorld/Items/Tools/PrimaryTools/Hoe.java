package models.GameWorld.Items.Tools.PrimaryTools;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Map.TerrainType;
import models.GameWorld.Map.Tile;

public class Hoe extends Tool {
    public Hoe() {
        super("Hoe", 0);
    }

    @Override
    protected int getMaxLevel() {
        return 4;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {
        Tile tile = player.getField().getTile(target);
        if (tile == null) return;

        if (tile.getTerrainType() == TerrainType.DIRT) {
            tile.setTerrainType(TerrainType.PLOWED_DIRT);
        }

        int energyConsumed = 5 - level;
        if (player.getSkills().getFarmingSkill().isMaxLevel()) energyConsumed--;
        player.changeEnergy(- (int) (energyConsumed * energyCoefficient(game.getWeather().getCurrentWeather())));
    }
}

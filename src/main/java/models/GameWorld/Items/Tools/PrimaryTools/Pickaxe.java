package models.GameWorld.Items.Tools.PrimaryTools;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Map.Elements.MapElement;
import models.GameWorld.Map.TerrainType;
import models.GameWorld.Map.Tile;
import models.GameWorld.Minerals.UnextractedMineral;

public class Pickaxe extends Tool {
    public Pickaxe() {
        super("Pickaxe", 0);
    }

    @Override
    protected int getMaxLevel() {
        return 4;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {
        int energyConsumed = 5 - level;

        Tile tile = player.getFarm().getTile(target);
        for (MapElement element : tile.getElements()) {
            if (element instanceof UnextractedMineral) {
                element.interact(player, target);
            } else if (tile.getTerrainType() == TerrainType.PLOWED_DIRT) {
                tile.setTerrainType(TerrainType.DIRT);
            }
        }

        if (player.getSkills().getMiningSkill().isMaxLevel()) energyConsumed--;
        int finalEnergyConsumption = (int) (
                Math.max(energyConsumed, 0) *
                energyCoefficient(game.getWeather().getCurrentWeather())
        );
        player.changeEnergy(-finalEnergyConsumption);
    }
}

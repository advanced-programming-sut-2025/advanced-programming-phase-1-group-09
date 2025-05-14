package models.GameWorld.Items.Tools.PrimaryTools;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Farming.Planted;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Map.Elements.MapElement;
import models.GameWorld.Map.Tile;

public class Scythe extends Tool {
    public Scythe() {
        super("Scythe", 0);
    }

    @Override
    protected int getMaxLevel() {
        return 0;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {
        Tile tile = player.getField().getTile(target);
        if (tile == null) return;

        for (MapElement e : tile.getElements()) {
            if (e.getName().equals("Grass")) {
                e.interact(player, target);
            } else if (e instanceof Planted) {
                e.interact(player, target);
            }
        }
        player.changeEnergy(- (int) (2 * energyCoefficient(game.getWeather().getCurrentWeather())));
    }
}

package models.GameWorld.Items.Tools.PrimaryTools;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Map.Elements.MapElement;

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
        for (MapElement e : player.getFarm().getTile(target).getElements()) {
            if (e.getName().equals("Grass")) {
                e.interact(player, target);
            }
        }
        player.changeEnergy(-2);
    }
}

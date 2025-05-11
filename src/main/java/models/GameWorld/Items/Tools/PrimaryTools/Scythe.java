package models.GameWorld.Items.Tools.PrimaryTools;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Tools.Tool;

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

    }
}

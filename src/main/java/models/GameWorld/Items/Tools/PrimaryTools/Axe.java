package models.GameWorld.Items.Tools.PrimaryTools;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Tools.Tool;


public class Axe extends Tool {
    public Axe() {
        super("Axe", 0);
    }

    @Override
    protected int getMaxLevel() {
        return 4;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

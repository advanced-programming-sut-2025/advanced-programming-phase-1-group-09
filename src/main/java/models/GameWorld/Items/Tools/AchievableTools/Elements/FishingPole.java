package models.GameWorld.Items.Tools.AchievableTools.Elements;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Tools.Tool;

public class FishingPole extends Tool {
    public FishingPole() {
        super("FishingPole", 1000);
    }

    @Override
    protected int getMaxLevel() {
        return 3;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

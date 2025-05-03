package models.GameWorld.Items.Tools.AchievableTools.Elements;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Tools.AchievableTools.AchievableTool;

public class MilkPail extends AchievableTool {
    public MilkPail() {
        super("MilkPail", 1000);
    }

    @Override
    protected int getMaxLevel() {
        return 0;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

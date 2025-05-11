package models.GameWorld.Items.Tools.PrimaryTools;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Farming.PlantedTree;
import models.GameWorld.Items.Tools.Tool;
import models.GameWorld.Map.Elements.MapElement;
import models.GameWorld.Map.Tile;


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
        int energyConsumed = 5 - level;

        Tile tile = player.getFarm().getTile(target);
        for (MapElement element : tile.getElements()) {
            if (element instanceof PlantedTree) {
                element.interact(player, target);
            }
        }

        if (player.getSkills().getForagingSkill().isMaxLevel()) energyConsumed--;
        player.changeEnergy(-Math.max(energyConsumed, 0));
    }
}

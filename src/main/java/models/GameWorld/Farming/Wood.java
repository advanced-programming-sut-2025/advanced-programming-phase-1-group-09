package models.GameWorld.Farming;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;
import models.GameWorld.Map.Elements.Collectable.Collectable;
import views.ConsoleColors;

public class Wood extends Item implements Collectable {
    public Wood() {
        super("Wood", true, 1);
    }

    @Override
    public Item collect() {
        return this;
    }

    @Override
    public int getRandom() {
        return random.nextInt(6) + 5;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }

    @Override
    public String getSymbol() {
        return ConsoleColors.YELLOW_BOLD_BRIGHT + "/";
    }
}

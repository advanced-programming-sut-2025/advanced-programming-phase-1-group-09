package models.GameWorld.Items.Minerals;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;
import models.GameWorld.Map.Elements.Collectable.Collectable;
import views.ConsoleColors;

public class Stone extends Item implements Collectable {
    public Stone() {
        super("Stone", true, 1);
    }

    @Override
    public Stone collect() {
        return this;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }

    @Override
    public String getSymbol() {
        return ConsoleColors.WHITE_BRIGHT + "○";
    }
}

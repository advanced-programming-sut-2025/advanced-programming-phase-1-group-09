package models.GameWorld.Map.Elements.Prefabs;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.Elements.MultiTileElement;
import views.ConsoleColors;

public class Hut extends Prefab {
    public Hut() {
        super(4, 4, 4, 80);
    }

    @Override
    public String getName() {
        return "Hut";
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void interact(Player player, Coordinate position) {}

    @Override
    public String getSymbol() {
        return ConsoleColors.WHITE_BACKGROUND + ConsoleColors.BLACK_BOLD +  "H";
    }
}

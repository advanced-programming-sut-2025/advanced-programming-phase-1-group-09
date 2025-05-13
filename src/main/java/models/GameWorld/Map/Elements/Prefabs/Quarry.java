package models.GameWorld.Map.Elements.Prefabs;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.Elements.MultiTileElement;
import views.ConsoleColors;

public class Quarry extends Prefab {
    public Quarry() {
        super(10, 10, 0, 0);
    }

    @Override
    public String getName() {
        return "Quarry";
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void interact(Player player, Coordinate position) {}

    @Override
    public String getSymbol() {
        return ConsoleColors.BLACK_BACKGROUND +  "Q";
    }
}

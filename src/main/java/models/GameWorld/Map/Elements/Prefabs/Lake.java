package models.GameWorld.Map.Elements.Prefabs;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.Elements.MultiTileElement;
import views.ConsoleColors;

public class Lake extends Prefab {
    public Lake(int height, int width, int y, int x) {
        super(height, width, y, x);
    }

    @Override
    public String getName() {
        return "Lake";
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void interact(Player player, Coordinate position) {}

    @Override
    public String getSymbol() {
        return ConsoleColors.BLUE_BACKGROUND_BRIGHT +  "L";
    }
}

package models.GameWorld.Farming;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.Elements.MapElement;
import views.ConsoleColors;

public class Grass implements MapElement {
    @Override
    public String getName() {
        return "Grass";
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void interact(Player player, Coordinate position) {
        if (player.getInventory().getCurrentTool().getName().equals("Scythe")) {
            player.getField().getTile(position).removeElement(this);
        }
    }

    @Override
    public String getSymbol() {
        return ConsoleColors.GREEN_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD_BRIGHT + "G";
    }
}

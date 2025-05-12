package models.GameWorld.Minerals;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.Miscellaneous.Inventory;
import models.GameWorld.Map.Elements.Collectable.Collectable;
import views.ConsoleColors;

import java.util.HashMap;

public class Mineral extends Item implements Collectable {
    private final String symbol;

    public Mineral(UnextractedMineral mineral) {
        super(mineral.getName(), true, mineral.getPrice());
        this.symbol = mineral.getSymbol();
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {}

    @Override
    public Item collect() {
        return this;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}

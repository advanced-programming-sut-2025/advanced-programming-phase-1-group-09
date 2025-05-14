package models.GameWorld.Map.Elements.Prefabs;

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
    public String getSymbol() {
        return ConsoleColors.BLACK_BACKGROUND +  "Q";
    }
}

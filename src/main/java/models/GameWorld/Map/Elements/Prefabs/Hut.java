package models.GameWorld.Map.Elements.Prefabs;

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
    public String getSymbol() {
        return ConsoleColors.WHITE_BACKGROUND + ConsoleColors.BLACK_BOLD +  "H";
    }
}

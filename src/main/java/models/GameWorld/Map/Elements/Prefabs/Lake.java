package models.GameWorld.Map.Elements.Prefabs;

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
    public String getSymbol() {
        return ConsoleColors.BLUE_BACKGROUND_BRIGHT +  "L";
    }
}

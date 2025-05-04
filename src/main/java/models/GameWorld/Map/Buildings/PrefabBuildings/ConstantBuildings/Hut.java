package models.GameWorld.Map.Buildings.PrefabBuildings.ConstantBuildings;

import models.GameWorld.Items.Miscellaneous.Refrigerator;
import models.GameWorld.Map.Buildings.PrefabBuildings.PrefabBuilding;

public class Hut extends PrefabBuilding {
    private final Refrigerator refrigerator;

    public Hut(Refrigerator refrigerator, int width, int height) {
        super(width, height);
        this.refrigerator = refrigerator;
    }
}

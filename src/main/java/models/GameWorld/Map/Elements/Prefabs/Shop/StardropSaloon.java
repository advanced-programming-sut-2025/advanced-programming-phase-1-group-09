package models.GameWorld.Map.Elements.Prefabs.Shop;

public class StardropSaloon extends Shop {
    public StardropSaloon() {
        super(6, 8, 65, 48, "Gus", 12, 24);
    }

    @Override
    public String getName() {
        return "Stardrop Saloon";
    }
}

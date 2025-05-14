package models.GameWorld.Map.Elements.Prefabs.Shop;

public class MarnieRanch extends Shop {
    public MarnieRanch() {
        super(10, 15, 30, 0, "Marnie", 9, 16);
    }

    @Override
    public String getName() {
        return "Marnie's Ranch";
    }
}

package models.GameWorld.Map.Elements.Prefabs.Shop;

public class FishShop extends Shop {
    public FishShop() {
        super(8, 10, 86, 60, "Willy", 9, 17);
    }

    @Override
    public String getName() {
        return "Fish Shop";
    }
}

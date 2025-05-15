package models.GameWorld.Map.Elements.Prefabs.Shop;

public class CarpenterShop extends Shop {
    public CarpenterShop() {
        super(8, 10, 0, 55, "Robin", 9, 20);
    }

    @Override
    public String getName() {
        return "Carpenter's Shop";
    }

    @Override
    public String getSymbol() {
        return "C";
    }
}

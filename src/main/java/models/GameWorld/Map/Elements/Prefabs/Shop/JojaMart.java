package models.GameWorld.Map.Elements.Prefabs.Shop;


public class JojaMart extends Shop {
    public JojaMart() {
        super(10, 15, 30, 100, "Morris", 9, 23);
    }

    @Override
    public String getName() {
        return "JojaMart";
    }
}

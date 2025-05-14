package models.GameWorld.Map.Elements.Prefabs.Shop;


public class Blacksmith extends Shop {
    public Blacksmith() {
        super(6, 8, 80, 108, "Clint", 9, 16);
    }

    @Override
    public String getName() {
        return "Blacksmith";
    }
}

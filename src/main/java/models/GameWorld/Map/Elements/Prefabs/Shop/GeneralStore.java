package models.GameWorld.Map.Elements.Prefabs.Shop;


public class GeneralStore extends Shop {
    public GeneralStore() {
        super(10, 15, 40, 55, "Pierre", 9, 17);
    }

    @Override
    public String getName() {
        return "Pierre's General Store";
    }
}

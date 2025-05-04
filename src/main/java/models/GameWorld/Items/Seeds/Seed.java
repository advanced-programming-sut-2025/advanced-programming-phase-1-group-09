package models.GameWorld.Items.Seeds;

import models.GameWorld.Items.Miscellaneous.Inventory;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.StackableItem;
import models.GameWorld.Season;

public class Seed extends StackableItem {
    private Season season;
    private Inventory inventory;

    public Seed(String name, int quantity, int price) {
        super(name, quantity, price);
    }
}

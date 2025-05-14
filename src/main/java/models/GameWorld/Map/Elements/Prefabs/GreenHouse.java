package models.GameWorld.Map.Elements.Prefabs;

import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;
import views.ConsoleColors;

public class GreenHouse extends Prefab {
    public final int NEEDED_WOOD = 500;
    public final int NEEDED_MONEY = 1000;
    private boolean isBuilt;

    public GreenHouse() {
        super(10, 12, 4, 30);
        this.isBuilt = false;
    }

    @Override
    public String getName() {
        return "GreenHouse";
    }

    @Override
    public String getSymbol() {
        return ConsoleColors.GREEN_BACKGROUND +  "#";
    }

    public boolean isBuilt() {
        return isBuilt;
    }

    public void build(Player player) {
        if (isBuilt) return;

        Item wood = player.getInventory().findItem("Wood");
        int amount = player.getMainInventory().getItemQuantity(wood);
        if (amount >= NEEDED_WOOD && player.getMoney() >= NEEDED_MONEY) {
            player.getInventory().getMainInventory().reduceItemQuantity(wood, NEEDED_WOOD);
            player.changeMoney(-NEEDED_MONEY);
            isBuilt = true;
        }
    }
}

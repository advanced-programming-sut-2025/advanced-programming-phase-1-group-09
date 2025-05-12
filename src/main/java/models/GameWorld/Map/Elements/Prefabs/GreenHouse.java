package models.GameWorld.Map.Elements.Prefabs;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;
import models.GameWorld.Map.Elements.MultiTileElement;
import views.ConsoleColors;

public class GreenHouse implements MultiTileElement {
    public final int NEEDED_WOOD = 500;
    public final int NEEDED_MONEY = 1000;
    private final int y;
    private final int x;
    private boolean isBuilt;

    public GreenHouse() {
        this.y = 4;
        this.x = 30;
        this.isBuilt = false;
    }

    @Override
    public int getHeight() {
        return 10;
    }

    @Override
    public int getWidth() {
        return 12;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public String getName() {
        return "Hut";
    }

    @Override
    public boolean isFixed() {
        return true;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void interact(Player player, Coordinate position) {}

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

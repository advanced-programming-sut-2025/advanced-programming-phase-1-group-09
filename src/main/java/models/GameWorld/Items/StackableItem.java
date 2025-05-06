package models.GameWorld.Items;

public abstract class StackableItem extends Item {
    private int quantity;
    private final int price;

    public StackableItem(String name, int price) {
        super(name, true);
        this.quantity = 1;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public void reduceQuantity(int amount) {
        this.quantity = Math.max(0, this.quantity - amount);
    }

    public int getPrice() {
        return price;
    }
}

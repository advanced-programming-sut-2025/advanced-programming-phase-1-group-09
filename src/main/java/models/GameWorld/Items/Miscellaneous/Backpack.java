package models.GameWorld.Items.Miscellaneous;

public enum Backpack {
    PRIMARY(0, 12),
    BIG(1000, 24),
    DELUXE(3000, -1); // -1 means unlimited

    private final int price;
    private final int capacity;

    Backpack(int price, int capacity) {
        this.price = price;
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }
}

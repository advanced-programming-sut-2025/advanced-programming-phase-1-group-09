package models.GameWorld.Entity.Player;

import models.GameWorld.Items.Item;

public class Gift {
    private static int idCounter = 0;
    private final int id;
    private final Item item;
    private final int amount;
    private int rate;

    public Gift(Item item, int amount) {
        this.id = idCounter++;
        this.item = item;
        this.amount = amount;
        this.rate = 0;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public int getRate() {
        return rate;
    }

    public void rate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "id=" + id +
                ", item=" + item.getName() +
                ", amount=" + amount +
                ", rate=" + (rate == 0 ? "unrated" : (rate + "/5")) +
                '}';
    }
}

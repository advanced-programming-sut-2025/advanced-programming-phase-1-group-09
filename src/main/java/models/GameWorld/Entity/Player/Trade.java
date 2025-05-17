package models.GameWorld.Entity.Player;

import models.Game;
import models.GameWorld.Items.Item;

public class Trade {
    private static int idCounter = 1;

    private final int id;
    private final String senderUsername;
    private final String receiverUsername;
    private final boolean request;
    private final Item item;
    private final int amount;
    private final Integer price; // null if not money-based
    private final Item targetItem;
    private final Integer targetAmount;

    public Trade(String senderUsername, String receiverUsername, boolean request, Item item, int amount,
                 Integer price, Item targetItem, Integer targetAmount) {
        this.id = idCounter++;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.request = request;
        this.item = item;
        this.amount = amount;
        this.price = price;
        this.targetItem = targetItem;
        this.targetAmount = targetAmount;
    }

    public int getId() {
        return id;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public boolean isRequest() {
        return request;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isMoneyBased() {
        return price != null;
    }

    public Integer getPrice() {
        return price;
    }

    public Item getTargetItem() {
        return targetItem;
    }

    public Integer getTargetAmount() {
        return targetAmount;
    }

    public Player getOther(Game game, String username) {
        if (username.equals(senderUsername)) {
            return game.getPlayer(receiverUsername);
        }
        return game.getPlayer(senderUsername);
    }

    @Override
    public String toString() {
        return String.format(
                "id=%d\n" +
                "%s from '%s' to '%s'\n" +
                "item=%s\n" +
                "amount=%d\n" +
                "%s",
                id,
                (request ? "A request" : "An offer"), senderUsername, receiverUsername,
                item, amount,
                (price != null ?
                        String.format("price=%d", price) :
                        String.format("targetItem=%s, targetAmount=%d", targetItem, targetAmount)
                )
        );
    }
}

package models.GameWorld.Entity.Player;

public class Partnership {
    private final Player player1;
    private final Player player2;
    private int sharedBalance;

    public Partnership(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.sharedBalance = player1.getBalance() + player2.getBalance();

        // Reset personal balances
        player1.setBalance(0);
        player2.setBalance(0);
    }

    public void deposit(int amount) {
        if (amount > 0) sharedBalance += amount;
    }

    public boolean withdraw(int amount) {
        if (amount > sharedBalance) return false;
        sharedBalance -= amount;
        return true;
    }

    public int getBalance() {
        return sharedBalance;
    }

    public boolean includes(Player player) {
        return player1 == player || player2 == player;
    }
}

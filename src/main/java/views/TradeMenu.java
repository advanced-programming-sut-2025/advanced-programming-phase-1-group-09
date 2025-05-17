package views;

import controllers.TradeMenuController;
import models.App;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Entity.Player.Trade;
import models.Result;

import java.util.Scanner;

public class TradeMenu implements AppMenu {
    @Override
    public void check(Scanner scanner) {
        Player current = App.getInstance().getCurrentGame().getCurrentPlayer();
        if (current.countNewTrades() != 0) {
            System.out.println("New Trades:");
            for (Trade t : current.getNewTrades()) {
                System.out.println(t.toString());
            }
            System.out.println("--------------------------------");
        }

        String input = scanner.nextLine().trim();
        Result result = TradeMenuController.processCommand(input);
        System.out.println(result.message());
    }

    public static void showTrades(Player player) {
        System.out.println("Trades");
        for (Trade t : player.getPendingTrades()) {
            System.out.println(t.toString());
            System.out.println("--------------------------------");
        }
    }

    public static void showTradeHistory(Player player) {
        System.out.println("Trade History");
        for (Trade t : player.getTradeHistory()) {
            System.out.println(t.toString());
        }
    }
}

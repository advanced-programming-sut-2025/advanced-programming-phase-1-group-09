package controllers;

import models.App;
import models.DataManagers.DataHolder;
import models.Game;
import models.GameWorld.Entity.Player.Partnership;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Entity.Player.PlayerFriendship;
import models.GameWorld.Entity.Player.Trade;
import models.GameWorld.Items.Item;
import models.Menu.Command;
import models.Menu.Menus;
import models.Menu.TradeMenuCommands;
import models.Result;
import views.TradeMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TradeMenuController {
    private static final Pattern tradePattern = Pattern.compile(TradeMenuCommands.Trade.getRegex());

    public static Result processCommand(String command) {
        TradeMenuCommands matchedCommand = Command.findCommand(command, TradeMenuCommands.values());
        return switch (matchedCommand) {
            case null -> Result.invalidCommand;
            case Trade -> trade(command);
            case ShowTradeList -> {
                TradeMenu.showTrades(App.getInstance().getCurrentGame().getCurrentPlayer());
                yield new Result(true, "");
            }
            case TradeResponse -> tradeResponse(command);
            case TradeHistory -> {
                TradeMenu.showTradeHistory(App.getInstance().getCurrentGame().getCurrentPlayer());
                yield new Result(true, "");
            }
            case ExitTradeMenu -> {
                App.getInstance().setCurrentMenu(Menus.Game);
                yield new Result(true, "Redirecting to the game...");
            }
        };
    }

    private static Result trade(String command) {
        Matcher matcher = tradePattern.matcher(command);
        if (!matcher.find()) return Result.invalidCommand;
        Game game = App.getInstance().getCurrentGame();

        // Player Validation
        String username = matcher.group(1);
        Player opponent = game.getPlayer(username);
        if (opponent == null) return new Result(false, "Player \"" + username + "\" not found!");

        // Type Validation
        String type = matcher.group(2);
        if (!type.equalsIgnoreCase("request") && !type.equalsIgnoreCase("offer"))
            return new Result(false, "Invalid trade type!");
        boolean request = type.equalsIgnoreCase("request");

        // Item Validation
        String itemName = matcher.group(3);
        Item item = DataHolder.getItem(itemName);
        if (item == null) return new Result(false, "Item \"" + itemName + "\" not found!");

        // Amount Validation
        int amount = Integer.parseInt(matcher.group(4));
        if (amount <= 0) return new Result(false, "Invalid amount!");

        // Offer Validation
        Player current = game.getCurrentPlayer();
        Item itemInInventory = current.getInventory().findItem(itemName);
        if (!request) {
            if (itemInInventory == null) return new Result(false, "You don't have the item to offer");
            else if (current.getMainInventory().getItemQuantity(itemInInventory) < amount)
                return new Result(false, "You don't have enough items to offer");
        }

        String price = matcher.group(5); // might be null
        String targetItemName = matcher.group(6); // might be null
        String targetAmount = matcher.group(7); // might be null

        // Request Validation
        if (request) {
            if (price != null && current.getBalance() < Integer.parseInt(price))
                return new Result(false, "You don't have enough balance to request this item!");
            else if (targetItemName != null && targetAmount != null) {
                Item targetItem = DataHolder.getItem(targetItemName);
                if (targetItem == null)
                    return new Result(false, "Item \"" + targetItemName + "\" not found!");
                int targetAmountInt = Integer.parseInt(targetAmount);
                if (targetAmountInt <= 0) return new Result(false, "Invalid target amount!");
                if (current.getMainInventory().getItemQuantity(targetItem) < targetAmountInt)
                    return new Result(false, "You don't have enough items to offer");
            }
        }

        Trade trade = new Trade(
                current.getUsername(), opponent.getUsername(), request, item.clone(), amount,
                (price == null ? null : Integer.parseInt(price)),
                (targetItemName == null ? null : DataHolder.getItem(targetItemName).clone()),
                (targetAmount == null ? null : Integer.parseInt(targetAmount))
        );
        current.addPendingTrade(trade);
        opponent.addPendingTrade(trade);

        return new Result(true, "Trade request sent successfully!");
    }

    private static Result tradeResponse(String command) {
        boolean accept = command.contains("accept");
        int id = Integer.parseInt(command.split("\\s+-i\\s+")[1]);
        Game game = App.getInstance().getCurrentGame();

        Trade trade = game.getCurrentPlayer().findPendingTrade(id);
        if (trade == null) return new Result(false, "Trade not found!");

        Player current = game.getCurrentPlayer();
        current.getPendingTrades().remove(trade);
        current.getTradeHistory().add(trade);

        Player other = trade.getOther(game, current.getUsername());
        other.getPendingTrades().remove(trade);
        other.getTradeHistory().add(trade);

        PlayerFriendship friendship = current.getFriendships().get(other.getUsername());
        PlayerFriendship otherFriendship = other.getFriendships().get(current.getUsername());

        if (accept) {
            friendship.addExperience(50);
            otherFriendship.addExperience(50);
            return new Result(true, "Trade accepted successfully!");
        } else {
            friendship.reduceExperience(30);
            otherFriendship.reduceExperience(30);
            return new Result(true, "Trade rejected successfully!");
        }

        /*
        * TODO
        *  transferring items to other player
         */
    }
}

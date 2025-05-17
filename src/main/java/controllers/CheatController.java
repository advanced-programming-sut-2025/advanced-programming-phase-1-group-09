package controllers;

import models.Game;
import models.GameWorld.Entity.Animals.BoughtAnimal;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Enums.WeatherType;
import models.GameWorld.Map.GameMap;
import models.Menu.CheatCommands;
import models.Menu.Command;
import models.Result;

import java.util.regex.Matcher;

public class CheatController {
    private Game game = null;

    public void setGame(Game game) {
        this.game = game;
    }

    public Result processCheat(String cheat) {
        if (game == null) return new Result(false, "Game is null!");

        CheatCommands matchedCheat = Command.findCommand(cheat, CheatCommands.values());
        return switch (matchedCheat) {
            case null -> Result.invalidCommand;
            case TimeCheat -> timeCheat(cheat);
            case DateCheat -> dateCheat(cheat);
            case ThorCheat -> thorCheat(cheat);
            case WeatherCheat -> weatherCheat(cheat);
            case SetEnergyCheat -> setEnergyCheat(cheat);
            case UnlimitedEnergyCheat -> unlimitedEnergyCheat();
            case AddBalanceCheat -> addBalanceCheat(cheat);
            case SetFriendshipCheat -> setFriendShipCheat(cheat);
            default -> new Result(false, "Coming Soon...");
        };
    }

    private Result timeCheat(String cheat) {
        String time = cheat.split("\\s+")[3];
        int hours;
        try {
            hours = Integer.parseInt(time);
        } catch (NumberFormatException e) {
            return new Result(
                    false,
                    "Invalid time format. Please input something like this:\n" +
                    "cheat advance time X"
            );
        }

        game.getTimeState().updateTime(hours);
        game.resetTurn();
        return new Result(true, "Time set to " + game.getTimeState().getFormattedTime());
    }

    private Result dateCheat(String cheat) {
        String date = cheat.split("\\s+")[3];
        int day;
        try {
            day = Integer.parseInt(date);
        } catch (NumberFormatException e) {
            return new Result(
                    false,
                    "Invalid date format. Please input something like this:\n" +
                            "cheat advance date X"
            );
        }

        game.getTimeState().updateDate(day);
        game.resetTurn();

        return new Result(
                true,
                "Date set to " + game.getTimeState().getDay()
        );
    }

    private Result thorCheat(String cheat) {
        String[] parts = cheat.split("\\s+-y\\s+|\\s+-x\\s+");
        int y = Integer.parseInt(parts[1]);
        int x = Integer.parseInt(parts[2]);
        GameMap.thunder(game.getCurrentPlayer().getField(), y, x);
        return new Result(true, "Time set to 00:00:00");
    }

    private Result weatherCheat(String cheat) {
        String weather = cheat.split("\\s+")[3];
        try {
            game.changeCurrentWeather(WeatherType.valueOf(weather.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return new Result(
                    false,
                    "Please select the weather between SUNNY, RAINY, SNOWY, STORM."
            );
        }
        return new Result(true, "Weather changed to " + weather.toUpperCase());
    }

    private Result setEnergyCheat(String cheat) {
        String energy = cheat.split("\\s+-v\\s+")[1];
        int amount;
        try {
            amount = Integer.parseInt(energy);
        } catch (NumberFormatException e) {
            return new Result(false, "Please enter a valid energy value.");
        }
        game.getCurrentPlayer().setEnergy(amount);
        return new Result(true, "Energy changed to " + energy);
    }

    private Result unlimitedEnergyCheat() {
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.changeEnergyUnlimited();
        return new Result(
                true,
                "Energy changed to " + (currentPlayer.isEnergyUnlimited() ? "unlimited" : "limited") +
                        " mode for " + currentPlayer.getName()
        );
    }

    private Result addBalanceCheat(String cheat) {
        int amount;
        try {
            amount = Integer.parseInt(cheat.split("\\s+")[2]);
        } catch (NumberFormatException e) {
            return new Result(false, "Please enter a valid amount for balance.");
        }
        game.getCurrentPlayer().changeMoney(amount);
        return new Result(
                true,
                "Balance changed to " + amount + " for " + game.getCurrentPlayer().getName() + "."
        );
    }

    private Result setFriendShipCheat(String cheat) {
        Matcher matcher = CheatCommands.SetFriendshipCheat.mathcer(cheat.trim());
        BoughtAnimal targetAnimal = null;
        if(!matcher.group("amount").trim().matches("\\d+"))
            return new Result(false,"The amount is invalid!");
        for(BoughtAnimal animal : game.getCurrentPlayer().getAnimals().values()){
            if(targetAnimal.getNickname().equals(matcher.group("name").trim()))
                targetAnimal = animal;
        }
        if(targetAnimal == null)
            return new Result(false,"Animal not found!");
        targetAnimal.setFriendShipScore(Integer.parseInt(matcher.group("amount").trim()));
        return new Result(true, "Friendship score changed to " + targetAnimal.getFriendShipScore() + ".");
    }
}

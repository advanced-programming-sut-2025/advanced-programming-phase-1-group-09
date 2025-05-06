package controllers;

import models.Game;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Enums.WeatherType;
import models.Menu.CheatCommands;
import models.Menu.Command;
import models.Result;

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
            case WeatherCheat -> weatherCheat(cheat);
            case SetEnergyCheat -> setEnergyCheat(cheat);
            case UnlimitedEnergyCheat -> unlimitedEnergyCheat();
            case AddBalanceCheat -> addBalanceCheat(cheat);
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
}

package controllers;

import models.Game;
import models.Menu.CheatCommands;
import models.Menu.Command;
import models.Menu.GameMenuCommands;
import models.Result;

public class GameMenuController {
    private final CheatController cheatController;
    private Game game;

    public GameMenuController() {
        this.cheatController = new CheatController();
        this.game = null;
    }

    public void setGame(Game game) {
        this.game = game;
        cheatController.setGame(game);
    }

    public Result processCommand(String command) {
        if (game == null) return new Result(false, "Game is null!");

        CheatCommands matchedCheatCommand = Command.findCommand(command, CheatCommands.values());
        if (matchedCheatCommand != null) return cheatController.processCheat(command);

        GameMenuCommands matchedGameCommand = Command.findCommand(command, GameMenuCommands.values());
        return switch (matchedGameCommand) {
            case null -> Result.invalidCommand;
            case NextTurn -> nextTurn();
            case ShowTime ->
                    new Result(true, game.getTimeState().getFormattedTime());
            case ShowDate ->
                    new Result(true, game.getTimeState().getFormattedDate(game.getCurrentSeason()));
            case ShowDateTime ->
                    new Result(true, game.getTimeState().getFormattedDateTime(game.getCurrentSeason()));
            case ShowDay ->
                    new Result(true, game.getTimeState().getDayOfTheWeek().toString());
            case ShowSeason ->
                    new Result(true, game.getCurrentSeason().toString());
            case ShowWeather ->
                    new Result(true, game.getWeather().getCurrentWeather().toString());
            case ShowWeatherForecast ->
                    new Result(true, game.getWeather().getNextDayWeather().toString());
            default -> new Result(false, "Coming Soon...");
        };
    }

    private Result nextTurn() {
        if (game.isItLastTurn()) game.getTimeState().updateTime(1);
        game.nextTurn();
        return new Result(true, "It's \"" + game.getCurrentPlayer().getName() + "\" turn now.");
    }

}

package controllers;

import models.Menu.CheatCommands;
import models.Menu.Command;
import models.Menu.GameMenuCommands;
import models.Result;

public class GameMenuController {
    private final CheatController cheatController = new CheatController();

    public Result processCommand(String command) {
        CheatCommands matchedCheatCommand = Command.findCommand(command, CheatCommands.values());
        if (matchedCheatCommand != null) return cheatController.processCheat(command);

        GameMenuCommands matchedGameCommand = Command.findCommand(command, GameMenuCommands.values());
        return switch (matchedGameCommand) {
            case null -> Result.invalidCommand;
            default -> new Result(false, "Coming Soon...");
        };
    }

}

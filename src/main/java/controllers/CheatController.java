package controllers;

import models.Menu.CheatCommands;
import models.Menu.Command;
import models.Result;

public class CheatController {
    public Result processCheat(String cheat) {
        CheatCommands matchedCheat = Command.findCommand(cheat, CheatCommands.values());
        return switch (matchedCheat) {
            case null -> Result.invalidCommand;
            case TimeCheat -> timeCheat(cheat);
            default -> new Result(false, "Coming Soon...");
        };
    }

    private Result timeCheat(String cheat) {
        return null;
    }
}

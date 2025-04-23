package controllers;

import models.App;
import models.Menu.Command;
import models.Menu.MainMenuCommands;
import models.Menu.MenuNames;
import models.Result;

public class MainMenuController {
    public Result processCommand(String command) {
        MainMenuCommands matchedCommand = Command.findCommand(command, MainMenuCommands.values());
        return switch (matchedCommand) {
            case null -> Result.invalidCommand;
            case MenuEnter -> changeMenu(command);
            case ShowCurrentMenu -> new Result(true, App.getInstance().getCurrentMenu().toString());
            case LogOut -> logOut();
            case Exit -> {
                App.getInstance().setCurrentMenu(MenuNames.Exit);
                yield new Result(true, "Exiting the game...");
            }
        };
    }

    private Result changeMenu(String command) {
        String menuName = command.split("\\s+")[2];
        switch (menuName.toLowerCase()) {
            case "avatar":
                App.getInstance().setCurrentMenu(MenuNames.Avatar);
                return new Result(true, "Redirecting to the Avatar Menu...");
            case "profile":
                App.getInstance().setCurrentMenu(MenuNames.Profile);
                return new Result(true, "Redirecting to the Profile Menu...");
            case "game":
                App.getInstance().setCurrentMenu(MenuNames.Game);
                return new Result(true, "Redirecting to the Game Menu...");
            default:
                return new Result(false, "Invalid menu name");
        }
    }

    private Result logOut() {
        App.getInstance().setCurrentUser(null);
        App.getInstance().setCurrentMenu(MenuNames.Login);
        return new Result(true, "Logged out successfully!");
    }
}

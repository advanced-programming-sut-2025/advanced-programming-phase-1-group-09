package controllers;

import controllers.DataManagers.LastUserManager;
import models.App;
import models.Menu.Command;
import models.Menu.LoginMenuCommands;
import models.Menu.MenuNames;
import models.Result;
import models.User;

import java.util.Map;

public class LoginMenuController {
    private final UserController userController = new UserController();

    public Result processCommand(String command) {
        LoginMenuCommands matchedCommand = Command.findCommand(command, LoginMenuCommands.values());
        return switch (matchedCommand) {
            case null -> Result.invalidCommand;
            case MenuEnter -> new Result(false, "You should login to access to the menus!");
            case MenuExit -> {
                App.getInstance().setCurrentMenu(MenuNames.Exit);
                yield new Result(true, "Exiting the game...");
            }
            case ShowCurrentMenu -> new Result(true, App.getInstance().getCurrentMenu().toString());
            case Register -> register(command);
            case Login -> login(command);
            case ForgetPassword -> forgetPassword(command);
            case GeneratePassword -> new Result(true, PasswordUtils.generate(10));
        };
    }

    private Result register(String command) {
        Map<String, String> fields = userController.parseRegisterCommand(command);
        String username = fields.get("username");
        String password = fields.get("password");
        String confirm = fields.get("confirm");
        String nickname = fields.get("nickname");
        String email = fields.get("email");
        String gender = fields.get("gender");

        Result result = userController.validateRegisterFields(username, password, confirm, nickname, email, gender);

        if (!result.success()) {
            String help = userController.getHelp(fields, result.message());
            if (help != null) return new Result(false, result.message() + "\n" + help);
            else return result;
        }

        User user = userController.registerUser(username, password, nickname, email, gender);
        return new Result(true, result.message(), user);
    }

    private Result login(String command) {
        Map<String, String> fields = userController.parseLoginCommand(command);

        User user = App.getInstance().getUserByUsername(fields.get("username"));
        if (user == null) return new Result(false, "There is no user with that username!");

        String hashedPassword = PasswordUtils.hashString(fields.get("password"));
        if (!user.getHashedPassword().equals(hashedPassword))
            return new Result(false, "Invalid password!");

        if (command.contains("-stay-logged-in")) {
            LastUserManager.saveCurrentUser(user);
        }

        App.getInstance().setCurrentUser(user);
        App.getInstance().setCurrentMenu(MenuNames.Main);
        return new Result(true, "Logged in successfully! Redirecting to main menu...");
    }

    private Result forgetPassword(String command) {
        Map<String, String> fields = userController.parseForgetPasswordCommand(command);

        Result result = userController.validateForgetPasswordFields(fields.get("username"), fields.get("answer"));
        if (!result.success()) {
            String help = userController.getHelp(fields, result.message());
            if (help != null) return new Result(false, result.message() + "\n" + help);
            else return result;
        }

        return result;
    }
}

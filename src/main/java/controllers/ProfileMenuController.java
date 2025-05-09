package controllers;

import models.App;
import models.Menu.Command;
import models.Menu.Menus;
import models.Menu.ProfileMenuCommands;
import models.Result;
import models.User;
import utils.PasswordUtils;
import utils.UserUtils;
import views.ProfileMenu;

public class ProfileMenuController {
    private final UserUtils userUtils = new UserUtils();

    public Result processCommand(String command) {
        ProfileMenuCommands matchedCommand = Command.findCommand(command, ProfileMenuCommands.values());
        return switch (matchedCommand) {
            case null -> Result.invalidCommand;
            case MenuExit -> {
                App.getInstance().setCurrentMenu(Menus.Main);
                yield new Result(true, "Redirecting to the Main Menu...");
            }
            case ShowCurrentMenu -> new Result(true, App.getInstance().getCurrentMenu().toString());
            case ChangeUsername -> changeUsername(command);
            case ChangeNickname -> changeNickname(command);
            case ChangeEmail -> changeEmail(command);
            case ChangePassword -> changePassword(command);
            case ShowProfile -> {
                ProfileMenu.showUserInformation(App.getInstance().getCurrentUser());
                yield new Result(true, "");
            }
        };
    }

    private Result changeUsername(String command) {
        String username = command.split("\\s+-u\\s+")[1];
        if (!userUtils.isUsernameValid(username))
            return new Result(false, "Invalid username format!");
        if (username.equals(App.getInstance().getCurrentUser().getUsername()))
            return new Result(false, "Username is the same as your current username!");
        if (userUtils.isUsernameTaken(username))
            return new Result(false, "Username is already taken!");
        App.getInstance().getCurrentUser().setUsername(username);
        return new Result(true, "Username changed successfully!");
    }

    private Result changeNickname(String command) {
        String nickname = command.split("\\s+-n\\s+")[1];
        if (nickname.equals(App.getInstance().getCurrentUser().getNickname()))
            return new Result(false, "Nickname is the same as your current nickname!");
        App.getInstance().getCurrentUser().setNickname(nickname);
        return new Result(true, "Nickname changed successfully!");
    }

    private Result changeEmail(String command) {
        String email = command.split("\\s+-e\\s+")[1];
        if (!userUtils.isEmailValid(email))
            return new Result(false, "Invalid email format!");
        if (email.equals(App.getInstance().getCurrentUser().getEmail()))
            return new Result(false, "Email is the same as your current email!");
        App.getInstance().getCurrentUser().setEmail(email);
        return new Result(true, "Email changed successfully!");
    }

    private Result changePassword(String command) {
        String[] parts = command.split("\\s+-p\\s+|\\s+-o\\s+");
        if (parts.length != 3) return Result.invalidCommand;
        User currentUser = App.getInstance().getCurrentUser();

        String newPassword = parts[1];
        String oldHashedPassword = PasswordUtils.hashString(parts[2]);

        if (!currentUser.getHashedPassword().equals(oldHashedPassword))
            return new Result(false, "Invalid password!");

        String passwordStatus = PasswordUtils.validate(newPassword);
        if (passwordStatus != null) return new Result(false, passwordStatus);

        String newHashedPassword = PasswordUtils.hashString(newPassword);
        if (newHashedPassword.equals(currentUser.getHashedPassword()))
            return new Result(false, "New password is the same as your current password!");

        currentUser.setHashedPassword(newHashedPassword);
        return new Result(true, "Password changed successfully!");
    }
}

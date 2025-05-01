package controllers;

import models.App;
import models.Game;
import models.GameWorld.Entity.Player.Player;
import models.Menu.Command;
import models.Menu.Menus;
import models.Menu.PreGameMenuCommands;
import models.Result;
import models.User;

import java.util.ArrayList;

public class PreGameMenuController {
    public Result processCommand(String command) {
        PreGameMenuCommands matchedCommand = Command.findCommand(command, PreGameMenuCommands.values());
        return switch (matchedCommand) {
            case null -> Result.invalidCommand;
            case MenuExit -> {
                App.getInstance().setCurrentMenu(Menus.Main);
                yield new Result(true, "Redirecting to the Main Menu...");
            }
            case ShowCurrentMenu -> new Result(true, App.getInstance().getCurrentMenu().toString());
            case NewGame -> processNewGameCommand(command);
            case ChooseMap, LoadGame -> new Result(false, "Coming soon...");
        };
    }

    private Result processNewGameCommand(String command) {
        String[] parts = command.split("\\s+");
        if (parts.length < 4) return new Result(false, "Empty fields!");
        if (parts.length > 6) return new Result(false, "Too many fields!");

        User currentUser = App.getInstance().getCurrentUser();
        if (currentUser.getActiveGame() != null) {
            return new Result(false, "You are already in a game!");
        }

        ArrayList<User> users = new ArrayList<>();

        for (int i = 3; i < parts.length; i++) {
            User user = App.getInstance().getUserByUsername(parts[i]);
            if (user == null) return new Result(false, "User \"" + parts[i] + "\" not found!");
            if (user.getActiveGame() != null) {
                return new Result(false, "User \"" + parts[i] + "\" is already in a game!");
            }
            users.add(user);
        }

        createNewGame(currentUser, users);
        App.getInstance().setCurrentMenu(Menus.Game);
        return new Result(true, "New game created!");
    }

    private void createNewGame(User currentUser, ArrayList<User> opponentUsers) {
        Player currentPlayer = new Player(currentUser.getUsername());

        // Add all Players to the List
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentPlayer);
        for (User user : opponentUsers) {
            players.add(new Player(user.getUsername()));
        }

        // Instantiate Game
        Game game = new Game(currentPlayer, players, currentUser);

        // Assign Game to App
        App.getInstance().addGame(game);

        // Assign Game to current User
        currentUser.setActiveGame(game);
        currentUser.addGameId(game.getId());

        // Assign Game to other Users
        for (User user : opponentUsers) {
            user.setActiveGame(game);
            user.addGameId(game.getId());
        }
    }
}

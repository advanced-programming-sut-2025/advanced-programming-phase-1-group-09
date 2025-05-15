package controllers;

import models.App;
import models.Game;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.GameMap;
import models.GameWorld.Map.PublicMap;
import models.Menu.Command;
import models.Menu.Menus;
import models.Menu.PreGameMenuCommands;
import models.Result;
import models.User;
import views.GameMenu;
import views.PreGameMenu;

import java.util.ArrayList;
import java.util.HashMap;

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
            case LoadGame -> new Result(false, "Coming soon...");
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
        users.add(currentUser);

        for (int i = 3; i < parts.length; i++) {
            User user = App.getInstance().getUserByUsername(parts[i]);
            if (user == null) return new Result(false, "User \"" + parts[i] + "\" not found!");
            if (user.getActiveGame() != null) {
                return new Result(false, "User \"" + parts[i] + "\" is already in a game!");
            }
            users.add(user);
        }

        Game game = createNewGame(users);
        GameMenu gameMenu = (GameMenu)Menus.Game.getMenuView();
        gameMenu.setGame(game);

        App.getInstance().setCurrentMenu(Menus.Game);
        return new Result(true, "New game created!");
    }

    private Game createNewGame(ArrayList<User> users) {
        HashMap<User, GameMap> gameMaps = PreGameMenu.getUsersAndMaps(users);

        // Create a public map
        GameMap publicMap = new PublicMap();

        // Add all Players to the List
        ArrayList<Player> players = new ArrayList<>();
        for (User user : users) {
            players.add(new Player(user.getUsername(), gameMaps.get(user), publicMap));
        }

        // Instantiate Game
        Game game = new Game(players, App.getInstance().getCurrentUser(), publicMap);

        // Assign Game to App
        App.getInstance().addGame(game);

        // Assign Game to other Users
        for (User user : users) {
            user.setActiveGame(game);
            user.addGameId(game.getId());
        }

        return game;
    }
}

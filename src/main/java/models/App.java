package models;

import models.DataManagers.LastUserManager;
import models.DataManagers.UserManager;
import models.Menu.Menus;

import java.util.ArrayList;

public class App {
    private final ArrayList<Game> games;
    private final ArrayList<User> users;
    private User currentUser;
    private Menus currentMenu;

    private App() {
        games = new ArrayList<>();
        users = UserManager.loadUsers();
    }

    private static App instance;
    public static App getInstance() {
        if (instance == null) {
            instance = new App();
            instance.currentUser = LastUserManager.loadCurrentUser();
            if (instance.currentUser == null) {
                instance.currentMenu = Menus.Login;
            } else {
                instance.currentMenu = Menus.Main;
            }
        }
        return instance;
    }

    public void setCurrentMenu(Menus currentMenu) {
        this.currentMenu = currentMenu;
    }

    public Menus getCurrentMenu() {
        return currentMenu;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}

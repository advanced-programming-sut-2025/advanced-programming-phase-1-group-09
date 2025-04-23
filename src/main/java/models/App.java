package models;

import controllers.DataManagers.LastUserManager;
import controllers.DataManagers.UserManager;
import models.Menu.MenuNames;

import java.util.ArrayList;

public class App {
    private ArrayList<Game> games;
    private ArrayList<User> users;
    private User currentUser;
    private MenuNames currentMenu;

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
                instance.currentMenu = MenuNames.Login;
            } else {
                instance.currentMenu = MenuNames.Main;
            }
        }
        return instance;
    }

    public void setCurrentMenu(MenuNames currentMenu) {
        this.currentMenu = currentMenu;
    }

    public MenuNames getCurrentMenu() {
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

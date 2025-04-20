package controllers;

import controllers.DataManagers.UserManager;
import models.App;

public class Exit {
    public static void end() {
        // This class added to do some necessary works before ending the program
        UserManager repository = new UserManager();
        repository.saveUsers(App.getInstance().getUsers());
        System.exit(0);
    }
}

package views;

import controllers.PreGameMenuController;
import models.GameWorld.Map.ForestMap;
import models.GameWorld.Map.GameMap;
import models.GameWorld.Map.StandardMap;
import models.Result;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PreGameMenu implements AppMenu {
    private static final PreGameMenuController controller = new PreGameMenuController();
    private static Scanner scanner;

    @Override
    public void check(Scanner scanner) {
        PreGameMenu.scanner = scanner;

        String input = scanner.nextLine().trim();
        Result result = controller.processCommand(input);
        System.out.println(result.message());
    }

    public static HashMap<User, GameMap> getUsersAndMaps(ArrayList<User> users) {
        HashMap<User, GameMap> result = new HashMap<>();
        for (User user : users) {
            boolean selected = false;
            while (!selected) {
                System.out.println("Select map for " + user.getUsername() + ":");
                String input = scanner.nextLine().trim();
                if (input.equals("1") || input.equalsIgnoreCase("standard")) {
                    result.put(user, new StandardMap());
                    selected = true;
                } else if (input.equals("2") || input.equalsIgnoreCase("forest")) {
                    result.put(user, new ForestMap());
                    selected = true;
                } else {
                    System.out.println("Invalid Input\n");
                    System.out.println("Please choose between these:");
                    System.out.println("1. standard");
                    System.out.println("2. forest\n");
                }
            }
        }
        return result;
    }
}

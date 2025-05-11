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
                System.out.println("\nPlease choose map type for " + user.getUsername() + ":");
                System.out.println("1. standard");
                System.out.println("2. forest");
                System.out.print("> ");
                String input = scanner.nextLine().trim().toLowerCase();

                switch (input) {
                    case "1", "standard" -> {
                        result.put(user, new StandardMap());
                        selected = true;
                    }
                    case "2", "forest" -> {
                        result.put(user, new ForestMap());
                        selected = true;
                    }
                    default -> System.out.println("Invalid input.");
                }
            }
        }
        return result;
    }
}

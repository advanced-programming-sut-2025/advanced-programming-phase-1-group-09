package models.DataManagers;

import models.App;
import models.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LastUserManager {
    private static final String FILE_PATH = "data/last_user.txt";

    public static void saveCurrentUser(User user) {
        try {
            if (user == null) {
                Files.deleteIfExists(Path.of(FILE_PATH));
            } else {
                Files.writeString(Path.of(FILE_PATH), user.getUsername());
            }
        } catch (IOException e) {
            System.out.println("Error saving user!");
        }
    }

    public static User loadCurrentUser() {
        try {
            if (!Files.exists(Path.of(FILE_PATH))) return null;

            String username = Files.readString(Path.of(FILE_PATH)).trim();
            if (username.isEmpty()) return null;

            return App.getInstance().getUsers().stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            System.out.println("Error loading user!");
            return null;
        }
    }

}

package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserManager {
    private static final String FILE_PATH = "data/users.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ArrayList<User> loadUsers() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                objectMapper.writeValue(file, new ArrayList<User>());
                System.out.println("Created new users.json file.");
                return new ArrayList<>();
            } catch (IOException e) {
                System.out.println("Failed to create users.json file.");
                return new ArrayList<>();
            }
        }

        try {
            return objectMapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println("Failed to load users.json file.");
            return new ArrayList<>();
        }
    }

    public static void saveUsers(ArrayList<User> users) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
        } catch (IOException e) {
            System.out.println("Failed to save users.json file.");
        }
    }
}

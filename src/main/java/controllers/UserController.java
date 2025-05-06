package controllers;

import models.App;
import models.GameWorld.Enums.Gender;
import models.Result;
import models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.*;

public class UserController {
    private final Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9-]+$");
    private final Pattern emailPattern = Pattern.compile(
            "^(?!.*\\.\\.)[a-zA-Z0-9](?:[a-zA-Z0-9._-]*[a-zA-Z0-9])?@" +
                   "(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+" +
                   "[a-zA-Z]{2,}$"
    );

    private final Pattern registerParser = Pattern.compile(
            "-u\\s+(\\S+)|" +
                    "-p\\s+(\\S+)\\s+(\\S+)|" +
                    "-n\\s+(\\S+)|" +
                    "-e\\s+(\\S+)|" +
                    "-g\\s+(\\S+)"
    );
    private final Pattern loginParser = Pattern.compile("-u\\s+(\\S+)|-p\\s+(\\S+)");
    private final Pattern forgetPasswordParser = Pattern.compile("-u\\s+(\\S+)|-a\\s+(\\S+)");

    public boolean isUsernameValid(String username) {
        if (username == null) return false;
        Matcher matcher = usernamePattern.matcher(username);
        return matcher.matches();
    }

    public boolean isUsernameTaken(String username) {
        for (User user : App.getInstance().getUsers()) {
            if (user.getUsername().equals(username)) return true;
        }
        return false;
    }

    public boolean isEmailValid(String email) {
        if (email == null) return false;
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public Map<String, String> parseRegisterCommand(String command) {
        Map<String, String> fields = new HashMap<>();

        Matcher matcher = registerParser.matcher(command);

        while (matcher.find()) {
            if (matcher.group(1) != null) fields.put("username", matcher.group(1));
            else if (matcher.group(2) != null && matcher.group(3) != null) {
                fields.put("password", matcher.group(2));
                fields.put("confirm", matcher.group(3));
            }
            else if (matcher.group(4) != null) fields.put("nickname", matcher.group(4));
            else if (matcher.group(5) != null) fields.put("email", matcher.group(5));
            else if (matcher.group(6) != null) fields.put("gender", matcher.group(6));
        }

        return fields;
    }

    public Result validateRegisterFields(String username, String password, String confirm,
                                         String nickname, String email, String gender) {
        if (username == null || password == null || confirm == null ||
            nickname == null || email == null || gender == null) {
            return new Result(false, "Missing or empty field!");
        }

        if (!isUsernameValid(username)) {
            return new Result(false, "Username should be alphanumeric and can contain hyphens.");
        }
        if (isUsernameTaken(username)) {
            return new Result(false, "Username is already taken.");
        }

        String passwordStatus = PasswordUtils.validate(password);
        if (passwordStatus != null) {
            return new Result(false, passwordStatus);
        }
        if (!password.equals(confirm)) {
            return new Result(false, "Password and confirmation do not match.");
        }

        if (!isEmailValid(email)) {
            return new Result(false, "Email format is not valid.");
        }

        String loweredGender = gender.toLowerCase();
        if (!loweredGender.equals("male") && !loweredGender.equals("female")) {
            return new Result(false, "Gender should be either male or female.");
        }

        return new Result(true, "Registration successful!");
    }

    public User registerUser(String username, String password, String nickname, String email, String gender) {
        Gender g = Gender.valueOf(gender.toUpperCase());
        String hashedPassword = PasswordUtils.hashString(password);
        User user = new User(username, hashedPassword, nickname, email, g);
        App.getInstance().addUser(user);
        return user;
    }

    public String getHelp(Map<String, String> fields, String message) {
        Random random = new Random();
        if (message.equals("Username is already taken.")) {
            String username = fields.get("username");
            while (isUsernameTaken(username)) {
                int number = random.nextInt(11);
                if (number == 10) username += "-";
                else username += number;
            }
            return "Suggested username: " + username;
        } else if (message.contains("Password") && !message.contains("confirmation")) {
            return "Suggested password: " + PasswordUtils.generate(10);
        }
        return null;
    }

    public Map<String, String> parseLoginCommand(String command) {
        Map<String, String> fields = new HashMap<>();

        Matcher matcher = loginParser.matcher(command);

        while (matcher.find()) {
            if (matcher.group(1) != null) fields.put("username", matcher.group(1));
            else if (matcher.group(2) != null) fields.put("password", matcher.group(2));
        }

        return fields;
    }

    public Map<String, String> parseForgetPasswordCommand(String command) {
        Map<String, String> fields = new HashMap<>();

        Matcher matcher = forgetPasswordParser.matcher(command);

        while (matcher.find()) {
            if (matcher.group(1) != null) fields.put("username", matcher.group(1));
            else if (matcher.group(2) != null) fields.put("answer", matcher.group(2).toLowerCase());
        }

        return fields;
    }

    public Result validateForgetPasswordFields(String username, String answer) {
        if (username == null || answer == null) {
            return new Result(false, "Missing or empty fields!");
        }

        User user = App.getInstance().getUserByUsername(username);
        if (user == null) {
            return new Result(false, "There is no user with that username!");
        }

        String hashedAnswer = PasswordUtils.hashString(answer);
        if (!user.getSecurityAnswer().equals(hashedAnswer)) {
            return new Result(false,
                    "You should answer to the security question first\n" + user.getSecurityQuestion());
        }

        return new Result(true, "Enter new password:", user);
    }
}

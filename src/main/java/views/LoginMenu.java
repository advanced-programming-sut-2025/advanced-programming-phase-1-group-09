package views;

import models.DataManagers.UserManager;
import controllers.LoginMenuController;
import models.DataManagers.SecurityQuestionManager;
import utils.PasswordUtils;
import models.App;
import models.Result;
import models.User;

import java.util.Scanner;

public class LoginMenu implements AppMenu {
    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Result result = controller.processCommand(input);
        System.out.println(result.message());

        if (result.success() && result.user() != null) {
            if (result.message().equals("Registration successful!")){
                showSecurityQuestions();
                while (!getSecurityFields(scanner, result.user())) {
                    System.out.println("Invalid index. Please try again.");
                }
                UserManager.saveUsers(App.getInstance().getUsers());
            } else if (result.message().equals("Enter new password:")) {
                setNewPassword(scanner, result.user());
            }
        }
    }

    private void showSecurityQuestions() {
        System.out.println("List of security questions:");
        int index = 1;
        for (String question : SecurityQuestionManager.getAllQuestions()) {
            System.out.println(index++ + ". " + question);
        }
    }

    private boolean getSecurityFields(Scanner scanner, User user) {
        System.out.print("Enter the security question number: ");
        String question;
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            question = SecurityQuestionManager.getQuestion(index);
            if (question == null) return false;
        } catch (NumberFormatException e) {
            return false;
        }

        System.out.print("Enter your answer to the question: ");
        String answer = scanner.nextLine().trim();
        String hashedAnswer = PasswordUtils.hashString(answer.toLowerCase());

        user.setSecurityQuestion(question);
        user.setSecurityAnswer(hashedAnswer);

        System.out.println("Security question set successfully.");
        return true;
    }

    private void setNewPassword(Scanner scanner, User user) {
        System.out.print("New Password: ");
        String password = scanner.nextLine().trim();
        String passwordStatus = PasswordUtils.validate(password);
        if (passwordStatus != null) {
            System.out.println(passwordStatus);
            return;
        }

        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.nextLine().trim();
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Please try again.");
            return;
        }

        user.setHashedPassword(PasswordUtils.hashString(password));
        System.out.println("Password set successfully.");
    }
}

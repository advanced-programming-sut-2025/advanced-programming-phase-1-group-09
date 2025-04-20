package views;

import controllers.ProfileMenuController;
import models.Result;
import models.User;

import java.util.Scanner;

public class ProfileMenu implements AppMenu {
    private final ProfileMenuController controller = new ProfileMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Result result = controller.processCommand(input);
        System.out.println(result.message());
    }

    public static void showUserInformation(User user) {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Nickname: " + user.getNickname());
        System.out.println("Maximum Money Earned in a Game: " + user.getMaximumMoneyGained());
        System.out.println("Total Game Played: " + user.getNumberOfGamesPlayed());
    }
}

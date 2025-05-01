package views;

import controllers.GameMenuController;
import models.Result;

import java.util.Scanner;

public class GameMenu implements AppMenu {
    private final GameMenuController controller = new GameMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Result result = controller.processCommand(input);
        System.out.println(result.message());
    }
}

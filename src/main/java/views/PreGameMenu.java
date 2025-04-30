package views;

import controllers.PreGameMenuController;
import models.Result;

import java.util.Scanner;

public class PreGameMenu implements AppMenu {
    private static final PreGameMenuController controller = new PreGameMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Result result = controller.processCommand(input);
        System.out.println(result.message());
    }
}

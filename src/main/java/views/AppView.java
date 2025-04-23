package views;

import models.App;
import models.Menu.MenuNames;

import java.util.Scanner;

public class AppView {
    private final Scanner scanner;
    public AppView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        System.out.println(
                ConsoleColors.YELLOW_BOLD_BRIGHT +
                "Welcome to Stardew Valley!" +
                ConsoleColors.RESET
        );

        while (true) {
            MenuNames currentMenu = App.getInstance().getCurrentMenu();
            AppMenu menu = currentMenu.getMenu();
            menu.check(scanner);
        }
    }
}

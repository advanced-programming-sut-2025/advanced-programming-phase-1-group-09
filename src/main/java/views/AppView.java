package views;

import models.App;
import models.Menu.Menus;

import java.util.Scanner;

public class AppView {
    private final Scanner scanner;
    public AppView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        System.out.println(
                ConsoleColors.YELLOW_BOLD_BRIGHT +
                "Welcome to Stardew Valley!\n" +
                ConsoleColors.RESET
        );

        while (true) {
            Menus currentMenu = App.getInstance().getCurrentMenu();
            AppMenu menuView = currentMenu.getMenuView();
            menuView.check(scanner);
        }
    }
}

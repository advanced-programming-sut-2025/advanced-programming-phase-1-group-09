package views;

import controllers.Exit;
import models.App;

import java.util.Scanner;

public class AppView {
    private final Scanner scanner;
    public AppView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("Welcome to Stardew Valley!");
        AppMenu login = new LoginMenu();
        AppMenu main = new MainMenu();
        AppMenu profile = new ProfileMenu();
        while (true) {
            switch (App.getInstance().getCurrentMenu()) {
                case Login -> login.check(scanner);
                case Main -> main.check(scanner);
                case Profile -> profile.check(scanner);
                case Exit -> Exit.end();
            }
        }
    }
}

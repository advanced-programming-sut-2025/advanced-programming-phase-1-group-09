package views;

import controllers.GameMenuController;
import models.Game;
import models.Result;

import java.util.Scanner;

public class GameMenu implements AppMenu {
    private final GameMenuController controller;
    private Scanner scanner;
    private Game game;

    public GameMenu() {
        this.controller = new GameMenuController();
        this.scanner = null;
        this.game = null;
    }

    @Override
    public void check(Scanner scanner) {
        this.scanner = scanner;
        String input = scanner.nextLine().trim();
        Result result = controller.processCommand(input);
        System.out.println(result.message());
    }

    /**
     * We should set the menu game before accessing it
     * This should be done for every new game and load game
     */
    public void setGame(Game game) {
        this.game = game;
        controller.setGame(game);
    }
}

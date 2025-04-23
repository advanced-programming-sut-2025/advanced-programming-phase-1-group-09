package views;

import java.util.Scanner;

public class ExitMenu implements AppMenu {
    @Override
    public void check(Scanner scanner) {
        end();
    }

    public void end() {
        // This class added to do some necessary works before ending the program
        System.exit(0);
    }
}

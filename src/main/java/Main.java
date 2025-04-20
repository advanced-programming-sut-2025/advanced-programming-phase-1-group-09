import views.AppView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppView appView = new AppView(scanner);
        appView.start();
    }
}

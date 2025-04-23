package models.Menu;

import views.*;

public enum MenuNames {
    Login(new LoginMenu()),
    Main(new MainMenu()),
    Profile(new ProfileMenu()),
    Avatar(new AvatarMenu()),
    Game(new GameMenu()),
    Trade(new TradeMenu()),
    GameOver(new GameOverMenu()),
    Exit(new ExitMenu());

    private final AppMenu menu;

    MenuNames(AppMenu menu) {
        this.menu = menu;
    }

    public AppMenu getMenu() {
        return menu;
    }
}

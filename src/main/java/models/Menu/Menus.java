package models.Menu;

import views.*;

public enum Menus {
    Login(new LoginMenu()),
    Main(new MainMenu()),
    Profile(new ProfileMenu()),
    Avatar(new AvatarMenu()),
    PreGame(new PreGameMenu()),
    Game(new GameMenu()),
    Trade(new TradeMenu()),
    GameOver(new GameOverMenu()),
    Exit(new ExitMenu());

    private final AppMenu menu;

    Menus(AppMenu menu) {
        this.menu = menu;
    }

    public AppMenu getMenuView() {
        return menu;
    }
}

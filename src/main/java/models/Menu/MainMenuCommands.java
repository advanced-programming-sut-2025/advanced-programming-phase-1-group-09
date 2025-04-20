package models.Menu;

public enum MainMenuCommands implements Command {
    MenuEnter("menu\\s+enter\\s+\\S+"),
    ShowCurrentMenu("show\\s+current\\s+menu"),
    LogOut("user\\s+logout");

    private final String regex;

    MainMenuCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}

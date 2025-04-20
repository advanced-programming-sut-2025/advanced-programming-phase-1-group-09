package models.Menu;

public enum GameMenuCommands implements Command {
    MenuEnter("menu\\s+enter\\s+\\S+"),
    MenuExit("menu\\s+exit"),
    ShowCurrentMenu("show\\s+current\\s+menu"),
    NewGame("game\\s+new\\s+-u.*");

    private final String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}

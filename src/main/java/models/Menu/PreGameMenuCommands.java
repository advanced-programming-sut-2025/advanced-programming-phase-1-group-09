package models.Menu;

public enum PreGameMenuCommands implements Command {
    MenuExit("menu\\s+exit"),
    ShowCurrentMenu("show\\s+current\\s+menu"),
    NewGame("game\\s+new\\s+-u.*"),
    LoadGame("load\\s+game");

    private final String regex;

    PreGameMenuCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}

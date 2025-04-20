package models.Menu;

public enum ProfileMenuCommands implements Command {
    MenuExit("menu\\s+exit"),
    ShowCurrentMenu("show\\s+current\\s+menu"),
    ChangeUsername("change\\s+username\\s+-u\\s+\\S+"),
    ChangeNickname("change\\s+nickname\\s+-n\\s+\\S+"),
    ChangeEmail("change\\s+email\\s+-e\\s+\\S+"),
    ChangePassword("change\\s+password\\s+-p\\s+\\S+\\s+-o\\s+\\S+"),
    ShowProfile("user\\s+info");

    private final String regex;

    ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}

package models.Menu;

public enum CheatCommands implements Command{
    TimeCheat("cheat\\s+advance\\s+time\\s+\\S+"),
    DateCheat("cheat\\s+advance\\s+date\\s+\\S+"),
    ThorCheat("cheat\\s+Thor\\s+-y\\s+\\d+\\s+-x\\s+\\d+"),
    WeatherCheat("cheat\\s+weather\\s+set\\s+\\S+"),
    SetEnergyCheat("energy\\s+set\\s+-v\\s+\\S+"),
    UnlimitedEnergyCheat("energy\\s+unlimited"),
    AddItemCheat("cheat\\s+add\\s+item\\s+-n\\s+\\S+\\s+-c\\s+\\d+"),
    SetFriendshipCheat("cheat\\s+set\\s+friendship\\s+(?=.*-n)(?=.*-c).*"),
    AddBalanceCheat("cheat\\s+add\\s+\\S+\\s+dollars");

    private final String regex;

    CheatCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}

package models.Menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CheatCommands implements Command{
    TimeCheat("cheat\\s+advance\\s+time\\s+\\S+"),
    DateCheat("cheat\\s+advance\\s+date\\s+\\S+"),
    ThorCheat("cheat\\s+Thor\\s+-y\\s+\\d+\\s+-x\\s+\\d+"),
    WeatherCheat("cheat\\s+weather\\s+set\\s+\\S+"),
    SetEnergyCheat("energy\\s+set\\s+-v\\s+\\S+"),
    UnlimitedEnergyCheat("energy\\s+unlimited"),
    AddItemCheat("cheat\\s+add\\s+item\\s+(?=.*-n)(?=.*-c).*"),
    SetFriendshipCheat("cheat\\s+set\\s+friendship\\s+\\s+-n\\s+(?<name>.*)\\s+-c\\s+(?<amount>.*)"),
    AddBalanceCheat("cheat\\s+add\\s+\\S+\\s+dollars");

    private final String regex;

    CheatCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }

    public Matcher mathcer(String input){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if(matcher.matches())
            return matcher;
        return null;
    }
}

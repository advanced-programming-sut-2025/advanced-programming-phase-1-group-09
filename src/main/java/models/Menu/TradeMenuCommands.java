package models.Menu;

public enum TradeMenuCommands implements Command {
    Trade("trade\\s+-u\\s+(\\S+)\\s+-t\\s+(\\S+)\\s+-i\\s+(\\S+)\\s+-a\\s+(\\d+)" +
            "(?:\\s+-p\\s+(\\d+)|\\s+-ti\\s+(\\S+)\\s+-ta\\s+(\\d+))"),
    ShowTradeList("trade\\s+list"),
    TradeResponse("trade\\s+response\\s+(accept|reject)\\s+-i\\d+"),
    TradeHistory("trade\\s+history"),
    ExitTradeMenu("exit");

    private final String regex;

    TradeMenuCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}

package models.Menu;

public enum TradeMenuCommands implements Command {
    Trade("trade\\s+(?=.*-u)(?=.*-t)(?=.*-i)(?=.*-a).*"),
    ShowTradeList("trade\\s+list"),
    TradeResponse("trade\\s+response\\s+(accept|reject)\\s+-i\\S+"),
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

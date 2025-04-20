package models.Menu;

public enum LoginMenuCommands implements Command {
    MenuEnter("menu\\s+enter\\s+\\S+"),
    MenuExit("menu\\s+exit"),
    ShowCurrentMenu("show\\s+current\\s+menu"),
    Register("register\\s+(?=.*-u)(?=.*-p)(?=.*-n)(?=.*-e)(?=.*-g).*"),
    Login("login\\s+(?=.*-u)(?=.*-p).*"),
    ForgetPassword("forget\\s+password\\s+(?=.*-u)(?=.*-a).*"),
    GeneratePassword("generate\\s+password");

    private final String regex;

    LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}

package models.Menu;

public interface Command {
    String getRegex();

    default boolean isItCommand(String input) {
        return (input != null) && input.matches(getRegex());
    }

    static <T extends Enum<T> & Command> T findCommand(String input, T[] commands) {
        for (T command : commands) {
            if (command.isItCommand(input)) {
                return command;
            }
        }
        return null;
    }
}

package models;

public record Result(boolean success, String message, User user) {
    public Result(boolean success, String message) {
        this(success, message, null);
    }

    public static final Result invalidCommand = new Result(false, "Invalid Command!");
}

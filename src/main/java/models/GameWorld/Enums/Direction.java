package models.GameWorld.Enums;

public enum Direction {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    public final int dx, dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction getDirection(int dx, int dy) {
        if (dx == 0 && dy == -1) return UP;
        if (dx == 0 && dy == 1) return DOWN;
        if (dx == -1 && dy == 0) return LEFT;
        if (dx == 1 && dy == 0) return RIGHT;
        return null;
    }
}

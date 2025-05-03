package models.GameWorld;

public class Coordinate {
    private int y;
    private int x;

    public Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void move(int dy, int dx) {
        y += dy;
        x += dx;
    }
}

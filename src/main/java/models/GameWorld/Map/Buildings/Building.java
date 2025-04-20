package models.GameWorld.Map.Buildings;

public class Building {
    private models.GameWorld.Coordinate coordinate;
    protected final int width;
    protected final int height;

    public Building(int width, int height) {
        this.width = width;
        this.height = height;
    }
}

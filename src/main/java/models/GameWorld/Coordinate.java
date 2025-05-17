package models.GameWorld;

import models.GameWorld.Enums.Place;

public record Coordinate(int y, int x, Place place) {
    public Coordinate(int y, int x) {
        this(y, x, Place.FARM);
    }

    public boolean isNeighbor(Coordinate c) {
        return Math.abs(this.y - c.y) <= 1 && Math.abs(this.x - c.x) <= 1;
    }
}

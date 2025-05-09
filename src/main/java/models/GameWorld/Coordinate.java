package models.GameWorld;

import models.GameWorld.Enums.Place;

public record Coordinate(int y, int x, Place place) {
    public Coordinate(int y, int x) {
        this(y, x, Place.FARM);
    }
}

package models.GameWorld.Entity.Animals;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Entity;

public class Animal implements Entity {
    private int x;
    private int y;
    private final int price;
    private int friendshipScore;
    private final String name;
    private String nickname;
    //private final ArrayList<AnimalProduct> products = null;

    public Animal(int price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(y, x);
    }
}

package models.GameWorld.Entity.Animals;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Entity;
import models.GameWorld.Entity.Player.Player;

public class Animal{
    private final int price;
    private final String name;
    //private final ArrayList<AnimalProduct> products = null;

    public Animal(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

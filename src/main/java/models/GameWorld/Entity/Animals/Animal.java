package models.GameWorld.Entity.Animals;

import models.GameWorld.Entity.Entity;
import models.GameWorld.Items.Edibles.AnimalEdibleProduct.AnimalEdibleProduct;

import java.util.ArrayList;

public class Animal implements Entity {
    private final int price;
    private int friendshipScore;
    private final ArrayList<AnimalEdibleProduct> products;

    public Animal(int price, ArrayList<AnimalEdibleProduct> products) {
        this.price = price;
        this.products = products;
    }
}

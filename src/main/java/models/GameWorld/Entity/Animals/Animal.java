package models.GameWorld.Entity.Animals;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Entity;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.AnimalProducts.AnimalProduct;

import java.util.ArrayList;

public class Animal{
    private final int price;
    private final String name;
    private final ArrayList<AnimalProduct> products;

    public Animal(int price, String name, ArrayList<AnimalProduct> products) {
        this.price = price;
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<AnimalProduct> getProducts() {
        return products;
    }
}

package models.GameWorld.Entity.Animals;

import models.GameWorld.Items.AnimalProducts.AnimalProduct;

import java.util.ArrayList;

public class Animal{
    private final int price;
    private final String name;
    private final ArrayList<AnimalProduct> products;
    private final String[] livingPlaces;

    public Animal(int price, String name, ArrayList<AnimalProduct> products, String[] livingPlaces) {
        this.price = price;
        this.name = name;
        this.products = products;
        this.livingPlaces = livingPlaces;
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

    @Override
    public Animal clone() {
        return new Animal(price,name,products,livingPlaces);
    }
}

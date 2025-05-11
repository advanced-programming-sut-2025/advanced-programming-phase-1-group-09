package models.GameWorld.Entity.Animals.BarnAnimals;

import models.GameWorld.Entity.Animals.Animal;
import models.GameWorld.Items.Edibles.AnimalEdibleProduct.AnimalEdibleProduct;

import java.util.ArrayList;

public class BarnAnimal extends Animal {

    public BarnAnimal(int price, ArrayList<AnimalEdibleProduct> products) {
        super(price, products);
    }
}

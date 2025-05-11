package models.GameWorld.Entity.Animals.CoopAnimals;

import models.GameWorld.Entity.Animals.Animal;
import models.GameWorld.Items.Edibles.AnimalEdibleProduct.AnimalEdibleProduct;

import java.util.ArrayList;

public class CoopAnimal extends Animal {
    public CoopAnimal(int price, ArrayList<AnimalEdibleProduct> products) {
        super(price, products);
    }
}

package models.GameWorld.Entity.Animals.CageAnimals;

import models.GameWorld.Entity.Animals.Animal;
import models.GameWorld.Items.Edibles.AnimalEdibleProduct.AnimalEdibleProduct;

import java.util.ArrayList;

public class CageAnimal extends Animal {
    public CageAnimal(int price, ArrayList<AnimalEdibleProduct> products) {
        super(price, products);
    }
}

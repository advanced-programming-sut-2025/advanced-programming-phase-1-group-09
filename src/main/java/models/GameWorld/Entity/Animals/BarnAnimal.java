package models.GameWorld.Entity.Animals;

import models.GameWorld.Items.AnimalProducts.AnimalProduct;

import java.util.ArrayList;

public class BarnAnimal extends Animal {
    public BarnAnimal(int price, String name, ArrayList<AnimalProduct> products, String[] livingPlaces) {
        super(price, name, products, livingPlaces);
    }

    //TODO
    /*public void getIntoBarn(String placeName) {
        if(Arrays.asList(livingPlaces).contains(placeName)) {
            switch (placeName) {
                case "Barn" -> livingBarn = Barn();
                case "Big Barn" -> livingBarn = BigBarn();
                case "Deluxe Barn" -> livingBarn = DeluxeBarn();
                default -> System.out.println("The Animal can not live in this place");
            }
        }
    }*/
}

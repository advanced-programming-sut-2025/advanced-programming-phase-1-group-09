package models.GameWorld.Entity.Animals;

import models.GameWorld.Items.AnimalProducts.AnimalProduct;
import models.GameWorld.Map.Elements.Buyable.Coops.CoopElement;

import java.util.ArrayList;

public class CoopAnimal extends Animal {
    private CoopElement livingCoop = null;
    public CoopAnimal(int price, String name, ArrayList<AnimalProduct> products, String[] livingPlaces) {
        super(price, name, products, livingPlaces);
    }

    //TODO
    /*public void getIntoBarn(String placeName) {
        if(Arrays.asList(livingPlaces).contains(placeName)) {
            switch (placeName) {
                case "Coop" -> livingCoop = Coop();
                case "Big Coop" -> livingCoop = BigCoop();
                case "Deluxe Coop" -> livingCoop = DeluxeCoop();
                default -> System.out.println("The Animal can not live in this place");
            }
        }
    }*/
}

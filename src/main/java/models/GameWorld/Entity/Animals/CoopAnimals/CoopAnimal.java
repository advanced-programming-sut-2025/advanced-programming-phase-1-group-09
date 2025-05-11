package models.GameWorld.Entity.Animals.CoopAnimals;

import models.GameWorld.Entity.Animals.Animal;
import models.GameWorld.Map.Elements.Buyable.Coops.CoopElement;

public class CoopAnimal extends Animal {
    private final String[] livingPlaces;
    private CoopElement livingCoop = null;
    public CoopAnimal(int price, String name, String[] livingPlaces) {
        super(price, name);
        this.livingPlaces = livingPlaces;
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

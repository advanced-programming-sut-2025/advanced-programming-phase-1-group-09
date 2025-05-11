package models.GameWorld.Entity.Animals.BarnAnimals;

import models.GameWorld.Entity.Animals.Animal;
import models.GameWorld.Map.Elements.Buyable.Barns.Barn;
import models.GameWorld.Map.Elements.Buyable.Barns.BarnElement;
import models.GameWorld.Map.Elements.Buyable.Barns.BigBarn;
import models.GameWorld.Map.Elements.Buyable.Barns.DeluxeBarn;

import java.util.Arrays;

public class BarnAnimal extends Animal {
    private final String[] livingPlaces;
    private BarnElement livingBarn = null;
    public BarnAnimal(int price, String name, String[] livingPlaces) {
        super(price, name);
        this.livingPlaces = livingPlaces;
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

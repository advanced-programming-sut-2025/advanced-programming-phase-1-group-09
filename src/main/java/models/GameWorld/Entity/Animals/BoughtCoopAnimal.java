package models.GameWorld.Entity.Animals;

import models.GameWorld.Entity.Entity;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.AnimalProducts.AnimalProduct;

import java.util.ArrayList;


public class BoughtCoopAnimal extends Animal implements Entity {

    public BoughtCoopAnimal(int price, String name, ArrayList<AnimalProduct> products, String[] livingPlaces) {
        super(price, name, products, livingPlaces);
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void interact(Player player) {

    }
}

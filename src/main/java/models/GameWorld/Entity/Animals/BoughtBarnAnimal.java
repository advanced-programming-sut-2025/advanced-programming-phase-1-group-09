package models.GameWorld.Entity.Animals;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Entity;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.AnimalProducts.AnimalProduct;
import models.GameWorld.Map.Elements.Buyable.Barns.BarnElement;

import java.util.ArrayList;


public class BoughtBarnAnimal extends BoughtAnimal implements Entity {

    public BoughtBarnAnimal(int price, String name, ArrayList<AnimalProduct> products, String[] livingPlaces, String nickname, BarnElement livingBarn, Coordinate coordinate) {
        super(price, name, products, livingPlaces, nickname, livingBarn, coordinate);
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void interact(Player player) {

    }
}
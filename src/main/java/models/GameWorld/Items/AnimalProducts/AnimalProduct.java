package models.GameWorld.Items.AnimalProducts;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Animals.Animal;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;

public class AnimalProduct extends Item {
    private final Animal animal;
    public AnimalProduct(String name, boolean isStackable, int price, Animal animal) {
        super(name, isStackable, price);
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }
}

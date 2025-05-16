package models.GameWorld.Entity.Animals;

import models.GameWorld.Entity.Entity;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.AnimalProducts.AnimalProduct;
import models.GameWorld.Map.Elements.Buyable.Barns.BarnElement;

import java.util.ArrayList;


public class BoughtBarnAnimal extends Animal implements Entity {
    private int friendShipScore = 0;
    private final String nickname;
    private final BarnElement livingBarn;

    public BoughtBarnAnimal(int price, String name, ArrayList<AnimalProduct> products, String[] livingPlaces, String nickname, BarnElement livingBarn) {
        super(price, name, products, livingPlaces);
        this.nickname = nickname;
        this.livingBarn = livingBarn;
    }


    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void interact(Player player) {

    }

    public BarnElement getLivingBarn() {
        return livingBarn;
    }

    public int getFriendShipScore() {
        return friendShipScore;
    }

    public String getNickname() {
        return nickname;
    }

    public void setFriendShipScore(int friendShipScore) {
        this.friendShipScore = friendShipScore;
    }
}

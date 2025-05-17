package models.GameWorld.Entity.Animals;

import models.GameWorld.Coordinate;
import models.GameWorld.Items.AnimalProducts.AnimalProduct;
import models.GameWorld.Map.Elements.Buyable.Barns.BarnElement;

import java.util.ArrayList;

public class BoughtAnimal extends Animal {
    private int friendShipScore = 0;
    private final String nickname;
    private final BarnElement livingBarn;
    private boolean isOut = false;
    private Coordinate coordinate;
    private boolean isFedToday = false;
    private boolean isPettedToday = false;

    public BoughtAnimal(int price, String name, ArrayList<AnimalProduct> products, String[] livingPlaces, String nickname, BarnElement livingBarn, Coordinate coordinate) {
        super(price, name, products, livingPlaces);
        this.nickname = nickname;
        this.livingBarn = livingBarn;
    }

    public int getFriendShipScore() {
        return friendShipScore;
    }

    public String getNickname() {
        return nickname;
    }

    public BarnElement getLivingBarn() {
        return livingBarn;
    }

    public boolean isOut() {
        return isOut;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setFriendShipScore(int friendShipScore) {
        this.friendShipScore = friendShipScore;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isFedToday() {
        return isFedToday;
    }

    public boolean isPettedToday() {
        return isPettedToday;
    }

    public void setFedToday(boolean fedToday) {
        isFedToday = fedToday;
    }

    public void setPetToday(boolean petToday) {
        isPettedToday = petToday;
    }
}

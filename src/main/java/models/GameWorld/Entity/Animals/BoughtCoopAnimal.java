package models.GameWorld.Entity.Animals;

import models.GameWorld.Entity.Entity;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Map.Elements.Buyable.Coops.CoopElement;


public class BoughtCoopAnimal extends Animal implements Entity {
    private int friendShipScore = 0;
    private final String nickname;
    private final CoopElement livingCoop;
    public BoughtCoopAnimal(int price, String name, String nickname, CoopElement livingCoop) {
        super(price, name);
        this.nickname = nickname;
        this.livingCoop = livingCoop;
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

    public CoopElement getLivingCoop() {
        return livingCoop;
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

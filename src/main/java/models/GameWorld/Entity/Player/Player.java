package models.GameWorld.Entity.Player;

import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Entity;
import models.User;

import java.util.ArrayList;

public class Player implements Entity {
    private final User user;
    private final String name;
    private Coordinate coordinate;
    private int energy;
    private int money;
    private PlayerSkills skills;
    private PlayerInventory inventory;
    private ArrayList<PlayerFriendship> friendships;
    private ArrayList<PlayerTrade> trades;
    private boolean isHome;
    private boolean isSleep;
    private boolean isFainted;
    private Player partner;

    public Player(User user) {
        this.user = user;
        this.name = user.getNickname();
    }
}

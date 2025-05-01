package models.GameWorld.Entity.Player;

import models.App;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Entity;
import models.User;

import java.util.ArrayList;

public class Player implements Entity {
    private final String username;
    private final String name;
    private final Coordinate coordinate;
    private int energy;
    private int money;
    private final PlayerSkills skills;
    private final PlayerInventory inventory;
    private final ArrayList<PlayerFriendship> friendships;
    private final ArrayList<PlayerTrade> trades;
    private boolean isHome;
    private boolean isSleep;
    private boolean isFainted;
    private Player partner;

    public Player(String username) {
        this.username = username;
        this.name = getUser().getNickname();
        this.coordinate = new Coordinate(0, 0);
        this.energy = 100;
        this.money = 0;
        this.skills = new PlayerSkills();
        this.inventory = new PlayerInventory();
        this.friendships = new ArrayList<>();
        this.trades = new ArrayList<>();
        this.isHome = false;
        this.isSleep = false;
        this.isFainted = false;
        this.partner = null;
    }

    public String getUsername() {
        return username;
    }

    public User getUser() {
        return App.getInstance().getUserByUsername(username);
    }
}

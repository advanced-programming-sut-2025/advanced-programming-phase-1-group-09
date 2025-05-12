package models.GameWorld.Entity.Player;

import models.App;
import models.GameWorld.Coordinate;
import models.GameWorld.Items.Miscellaneous.Inventory;
import models.GameWorld.Map.GameMap;
import models.GameWorld.TimeState;
import models.TimeObserver;
import models.User;

import java.util.ArrayList;

public class Player implements Entity, TimeObserver {
    public static final int INITIAL_ENERGY = 200;

    private final String username;
    private final String name;
    private final GameMap farm;
    private Coordinate coordinate;
    private int maxEnergy;
    private int energy;
    private boolean isEnergyUnlimited;
    private int money;
    private final PlayerSkills skills;
    private final PlayerInventory inventory;
    private final ArrayList<PlayerFriendship> friendships;
    private final ArrayList<PlayerTrade> trades;
    private boolean isHome;
    private boolean isSleep;
    private boolean isFainted;
    private Player partner;

    public Player(String username, GameMap farm) {
        this.username = username;
        this.name = getUser().getNickname();
        this.farm = farm;
        this.coordinate = farm.getStartingPoint();
        this.maxEnergy = INITIAL_ENERGY;
        this.energy = INITIAL_ENERGY;
        this.isEnergyUnlimited = false;
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

    @Override
    public void onTimeChange(TimeState newState) {
        /*
        * TODO:
        *  Bottom of page 22 of the document should be implemented here
        *  All works related to entering next day
        */
        if (isFainted) {
            this.energy = (int) (0.75 * INITIAL_ENERGY);
            isFainted = false;
        } else {
            this.energy = INITIAL_ENERGY;
        }
    }

    public String getUsername() {
        return username;
    }

    public User getUser() {
        return App.getInstance().getUserByUsername(username);
    }

    public String getName() {
        return name;
    }

    public GameMap getFarm() {
        return farm;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = Math.min(energy, this.maxEnergy);
        if (this.energy <= 0) {
            this.energy = 0;
            isFainted = true;
        }
    }

    /**
     * Positive input to increase energy,
     * Negative input to decrease energy
     */
    public void changeEnergy(int energy) {
        if (isEnergyUnlimited) return;
        setEnergy(this.energy + energy);
    }

    public boolean isEnergyUnlimited() {
        return isEnergyUnlimited;
    }

    public void changeEnergyUnlimited() {
        isEnergyUnlimited = !isEnergyUnlimited;
    }

    public int getMoney() {
        return money;
    }

    /**
     * Positive input to increase money,
     * Negative input to decrease money
     */
    public void changeMoney(int money) {
        this.money = Math.max(this.money + money, 0);
    }

    public PlayerSkills getSkills() {
        return skills;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public Inventory getMainInventory() {
        return inventory.getMainInventory();
    }

    public boolean isFainted() {
        return isFainted;
    }

    public void setFainted(boolean fainted) {
        this.isFainted = fainted;
    }
}

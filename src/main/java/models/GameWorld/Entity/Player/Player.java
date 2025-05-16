package models.GameWorld.Entity.Player;

import models.App;
import models.DataManagers.DataHolder;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Animals.Animal;
import models.GameWorld.Entity.Entity;
import models.GameWorld.Enums.Direction;
import models.GameWorld.Enums.Gender;
import models.GameWorld.Farming.ForagingCrop;
import models.GameWorld.Items.Miscellaneous.Inventory;
import models.GameWorld.Items.Recipes.Recipe;
import models.GameWorld.Map.Elements.Collectable.Collectable;
import models.GameWorld.Map.GameMap;
import models.GameWorld.Map.Tile;
import models.GameWorld.TimeState;
import models.TimeObserver;
import models.User;
import utils.PathUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player implements Entity, TimeObserver {
    public static final int INITIAL_ENERGY = 200;

    private final String username;
    private final String name;
    private final Gender gender;
    private final GameMap farm;
    private final GameMap publicMap;
    private boolean isHome;
    private Coordinate coordinate;
    private int maxEnergy;
    private int energy;
    private boolean isEnergyUnlimited;
    private int balance;
    private final PlayerSkills skills;
    private final PlayerInventory inventory;
    private final HashMap<String, PlayerFriendship> friendships;
    private boolean isRejected;
    private int depressionDaysCount;
    private final ArrayList<PlayerTrade> trades;
    private final HashMap<String, Animal> animals;
    private final HashMap<String, Recipe> craftingRecipes = new HashMap<>();

    private boolean isSleep;
    private boolean isFainted;
    private Partnership partnership;

    public Player(String username, GameMap farm, GameMap publicMap) {
        this.username = username;
        this.name = getUser().getNickname();
        this.gender = getUser().getGender();
        this.farm = farm;
        this.publicMap = publicMap;
        this.isHome = true;
        this.coordinate = farm.getStartingPoint();
        this.maxEnergy = INITIAL_ENERGY;
        this.energy = INITIAL_ENERGY;
        this.isEnergyUnlimited = false;
        this.balance = 0;
        this.skills = new PlayerSkills();
        this.inventory = new PlayerInventory();
        this.friendships = new HashMap<>();
        this.isRejected = false;
        this.depressionDaysCount = 0;
        this.trades = new ArrayList<>();
        this.animals = new HashMap<>();
        this.isSleep = false;
        this.isFainted = false;
        this.partnership = null;
    }

    @Override
    public void onTimeChange(TimeState newState) {
        /*
        * TODO:
        *  Bottom of page 22 of the document should be implemented here
        *  All works related to entering next day
        */
        // Go Home
        List<Coordinate> path = PathUtils.findPath(getField(), coordinate, farm.getStartingPoint());
        if (energy < PathUtils.calculateEnergy(path)) isFainted = true;
        coordinate = farm.getStartingPoint();
        isHome = true;

        if (isRejected) {
            energy = (int) (0.5 * maxEnergy);
            if (++depressionDaysCount >= 7) {
                isRejected = false;
                depressionDaysCount = 0;
            }
        } else if (isFainted) {
            this.energy = (int) (0.75 * maxEnergy);
            isFainted = false;
        } else {
            this.energy = maxEnergy;
        }

        for (PlayerFriendship friendship : friendships.values()) {
            if (!friendship.isGreeted()) friendship.reduceExperience(10);
            friendship.setGreeted(false);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isInteractable() {
        return true;
    }

    @Override
    public void interact(Player player) {}

    public String getUsername() {
        return username;
    }

    public User getUser() {
        return App.getInstance().getUserByUsername(username);
    }

    public Gender getGender() {
        return gender;
    }

    public GameMap getField() {
        return isHome ? farm : publicMap;
    }

    @Override
    public boolean isInteractable() {
        return true;
    } //???

    @Override
    public void interact(Player player) {

    } //???

    public GameMap getFarm() {
        return farm;
    }

    public GameMap getPublicMap() {
        return publicMap;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setIsHome(boolean condition) {
        isHome = condition;
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

    public int getBalance() {
        return (partnership != null) ? partnership.getBalance() : balance;
    }

    public void setBalance(int balance) {
        if (partnership != null) return;
        this.balance = balance;
    }

    public void deposit(int amount) {
        if (partnership != null) {
            partnership.deposit(amount);
        } else {
            if (amount > 0) balance += amount;
        }
    }

    public boolean withdraw(int amount) {
        if (partnership != null) {
            return partnership.withdraw(amount);
        } else {
            if (amount > balance) return false;
            balance -= amount;
            return true;
        }
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

    public HashMap<String, PlayerFriendship> getFriendships() {
        return friendships;
    }

    public String getNewMessages() {
        String messages = "";
        for (PlayerFriendship friendship : friendships.values()) {
            int newMessages = friendship.countNewMessages();
            if (newMessages != 0) {
                messages += String.format(
                        "You have %d new message%s from %s!\n",
                        newMessages,
                        newMessages == 1 ? "" : "s",
                        friendship.getEntity().getName()
                );
            }
        }
        return messages;
    }

    public PlayerFriendship findFriendship(int receivedGiftId) {
        for (PlayerFriendship friendship : friendships.values()) {
            for (Gift gift : friendship.getReceivedGifts()) {
                if (gift.getId() == receivedGiftId) return friendship;
            }
        }
        return null;
    }

    public String getNewProposals() {
        if (gender == Gender.MALE || partnership != null) return "";

        String proposal = "";
        for (PlayerFriendship friendship : friendships.values()) {
            int newProposals = friendship.countNewProposals();
            if (newProposals != 0) {
                proposal += String.format(
                        "You have a proposal from %s!\n",
                        friendship.getEntity().getName()
                );
            }
        }

        return proposal;
    }

    public void setRejected(boolean rejected) {
        isRejected = rejected;
    }

    public boolean isFainted() {
        return isFainted;
    }

    public void setFainted(boolean fainted) {
        this.isFainted = fainted;
    }

    public void setPartnership(Partnership partnership) {
        this.partnership = partnership;
    }

    public void collectAround() {
        for (Direction direction : Direction.values()) {
            Coordinate position = new Coordinate(coordinate.y() + direction.dy, coordinate.x() + direction.dx);
            Tile tile = getField().getTile(position);
            if (tile == null) continue;

            tile.getElements().removeIf(element -> {
                if (element instanceof Collectable collectable) {
                    if (getMainInventory().addItem(collectable.collect(), collectable.getRandomQuantity())) {
                        if (collectable instanceof ForagingCrop) {
                            getSkills().getForagingSkill().addExperience(10);
                        }
                        return true; // Remove the element
                    }
                }
                return false; // Keep the element
            });
        }
    }

    public void setInitialCraftingRecipes() {
        craftingRecipes.put("Furnace", DataHolder.getRecipe("Furnace"));
        craftingRecipes.put("ScareCrow", DataHolder.getRecipe("ScareCrow"));
        craftingRecipes.put("Mayonnaise Machine", DataHolder.getRecipe("Mayonnaise Machine"));
    }

    public HashMap<String, Recipe> getCraftingRecipes() {
        return craftingRecipes;
    }
}

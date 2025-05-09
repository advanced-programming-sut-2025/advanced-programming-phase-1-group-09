package models.GameWorld.Farming;

import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Items.Item;
import models.GameWorld.Items.StackableItem;
import models.GameWorld.Map.Elements.Collectable.Collectable;

import java.util.HashSet;
import java.util.Set;

public class ForagingCrop extends StackableItem implements Edible, Collectable {
    private final int gainedEnergy;
    private final Set<SeasonName> seasons;
    private final double spawningChance;

    public ForagingCrop(String name, int price, int gainedEnergy, Set<SeasonName> seasons, double spawningChance) {
        super(name, price);
        this.gainedEnergy = gainedEnergy;
        this.seasons = seasons;
        this.spawningChance = spawningChance;
    }

    @Override
    public int getGainedEnergy() {
        return gainedEnergy;
    }

    public Set<SeasonName> getSeasons() {
        return seasons;
    }

    public double getSpawningChance() {
        return spawningChance;
    }

    @Override
    public ForagingCrop clone() {
        return new ForagingCrop(name, price, gainedEnergy, new HashSet<>(seasons), spawningChance);
    }

    @Override
    public Item collect() {
        return this;
    }
}

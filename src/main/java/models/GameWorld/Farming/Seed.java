package models.GameWorld.Farming;

import models.GameWorld.Items.Item;
import models.GameWorld.Items.StackableItem;
import models.GameWorld.Map.Elements.Collectable.Collectable;

/**
 * Don't use raw seeds from metadata.
 * Instead, use it like this: <p>
 * Seed treeSeed = TreeMetaData.getSeed(seedName).newSeed(amount)
 * <p> or <p>
 * Seed cropSeed = CropMetaData.getSeed(seedName).newSeed(amount)
 */
public class Seed extends StackableItem implements Collectable {
    // We can access the related crop via CropMetaData

    // This will be needed for foraging
    private final double spawningChance;

    public Seed(String name, int price, double spawningChance) {
        super(name, price);
        this.spawningChance = spawningChance;
    }

    public double getSpawningChance() {
        return spawningChance;
    }

    public Seed newSeed(int amount) {
        Seed seed = new Seed(this.getName(), this.getPrice(), spawningChance);
        seed.setQuantity(amount);
        return seed;
    }

    @Override
    public Item collect() {
        return this;
    }
}

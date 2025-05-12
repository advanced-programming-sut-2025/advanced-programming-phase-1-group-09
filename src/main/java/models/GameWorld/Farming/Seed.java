package models.GameWorld.Farming;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Item;
import models.GameWorld.Map.Elements.Collectable.Collectable;
import views.ConsoleColors;

/**
 * Don't use raw seeds from metadata.
 * Instead, use it like this: <p>
 * <blockquote><pre>
 *     Seed treeSeed = TreeMetaData.getSeed(seedName).clone()
 * </pre></blockquote>
 * <p> or <p>
 * <blockquote><pre>
 *     Seed cropSeed = CropMetaData.getSeed(seedName).clone()
 * </pre></blockquote>
 */
public class Seed extends Item implements Collectable {
    // This will be needed for foraging
    private final double spawningChance;
    private final boolean isCropSeed;

    public Seed(String name, int price, double spawningChance, boolean isCropSeed) {
        super(name, true, price);
        this.spawningChance = spawningChance;
        this.isCropSeed = isCropSeed;
    }

    public double getSpawningChance() {
        return spawningChance;
    }

    public boolean isCropSeed() {
        return isCropSeed;
    }

    @Override
    public Seed clone() {
        return new Seed(name, price, spawningChance, isCropSeed);
    }

    @Override
    public Item collect() {
        return this;
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {

    }

    @Override
    public String getSymbol() {
        return ConsoleColors.GREEN_BOLD_BRIGHT + "•";
    }
}

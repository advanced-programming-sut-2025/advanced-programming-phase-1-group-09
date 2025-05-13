package models.GameWorld.Farming;

import models.Game;
import models.GameWorld.Coordinate;
import models.GameWorld.Entity.Player.Player;
import models.GameWorld.Items.Edibles.Edible;
import models.GameWorld.Items.Item;
import models.GameWorld.Map.Elements.Collectable.Collectable;

public class ItemCrop extends Item implements Edible, Collectable {
    private final CropDefinition definition;

    public ItemCrop(CropDefinition definition) {
        super(definition.name(), true, definition.baseSellPrice());
        this.definition = definition;
    }

    @Override
    public int getGainedEnergy() {
        return definition.baseEnergy();
    }

    @Override
    public void use(Coordinate target, Player player, Game game) {
    }

    @Override
    public Item collect() {
        return this;
    }
}

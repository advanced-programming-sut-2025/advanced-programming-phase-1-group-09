package models.GameWorld.Items.Edibles;

import models.GameWorld.Entity.Player.Player;

public interface Edible {
    int getGainedEnergy();

    default void eat(Player player) {
        player.changeEnergy(getGainedEnergy());
    }
}

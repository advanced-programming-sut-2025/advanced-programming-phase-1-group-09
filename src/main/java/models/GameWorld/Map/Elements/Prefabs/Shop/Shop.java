package models.GameWorld.Map.Elements.Prefabs.Shop;

import models.GameWorld.Map.Elements.MultiTileElement;

public abstract class Shop implements MultiTileElement {
    private final String shopName;
    private final String ownerName;
    private final int startingHour;
    private final int endingHour;

}

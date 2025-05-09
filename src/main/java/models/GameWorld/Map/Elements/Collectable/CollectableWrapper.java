package models.GameWorld.Map.Elements.Collectable;

import java.util.function.Supplier;

public record CollectableWrapper(double chance, Supplier<Collectable> itemSupplier) {}

package models.GameWorld.Items.Recipes;

import models.GameWorld.Items.Item;

import java.util.List;

public record Ingredient(Item item, int quantity, List<Item> otherOptions) {

}

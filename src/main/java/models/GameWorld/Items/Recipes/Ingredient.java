package models.GameWorld.Items.Recipes;

import models.GameWorld.Items.Item;
import models.GameWorld.Items.StackableItem;

public record Ingredient(Item item,int quantity){
}

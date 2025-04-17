package models.GameWorld.Map.Buildings.PrefabBuildings.RandomGenerateForagings.Trees;

import models.GameWorld.Items.Item;

import java.util.ArrayList;

public class Tree {
    private final Item source;
    private final Item fruit;
    private final ArrayList<Integer> stages;
    private final int totalHarvestTime;
    private boolean isBurnt;
    private final int seedGivenAfterCutting;
}

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

    public Tree(Item source, Item fruit, ArrayList<Integer> stages, int totalHarvestTime, int seedGivenAfterCutting) {
        this.source = source;
        this.fruit = fruit;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.seedGivenAfterCutting = seedGivenAfterCutting;
    }
}

package models.GameWorld.Map.Map;

import models.GameWorld.Map.Buildings.PrefabBuildings.ConstantBuildings.GreenHouse;
import models.GameWorld.Map.Buildings.PrefabBuildings.ConstantBuildings.Hut;
import models.GameWorld.Map.Buildings.PrefabBuildings.ConstantBuildings.Lake;
import models.GameWorld.Map.Buildings.PrefabBuildings.ConstantBuildings.Quarry;
import models.GameWorld.Map.Buildings.PrefabBuildings.RandomGenerateForagings.Rocks.Rock;
import models.GameWorld.Map.Buildings.PrefabBuildings.RandomGenerateForagings.Trees.Tree;

import java.util.ArrayList;

public class Map {
    //Constant Elements
    protected ArrayList<Lake> lakes;
    protected GreenHouse greenHouse;
    protected Quarry quarry;
    protected Hut hut;

    //Random Elements
    protected ArrayList<Rock> rocks;
    protected ArrayList<Tree> trees;
    // protected ArrayList<Foraging> foragings;

    //Game Map
    protected int[][] map;
}

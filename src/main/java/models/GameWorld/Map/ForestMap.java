package models.GameWorld.Map;

import models.DataManagers.MineralMetaData;
import models.DataManagers.TreeMetaData;
import models.GameWorld.Coordinate;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Farming.PlantedTree;
import models.GameWorld.Map.Elements.Collectable.CollectableHolder;
import models.GameWorld.Map.Elements.Prefabs.GreenHouse;
import models.GameWorld.Map.Elements.Prefabs.Hut;
import models.GameWorld.Map.Elements.Prefabs.Lake;
import models.GameWorld.Map.Elements.Prefabs.Quarry;
import models.GameWorld.TimeState;

import java.util.Random;

public class ForestMap extends GameMap {
    public ForestMap() {
        super(80, 100, new Coordinate(12, 99));
    }

    @Override
    protected void initTiles() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Tile(TerrainType.DIRT);
            }
        }
    }

    @Override
    protected void placeStaticElements() {
        setPrefab(new Quarry());
        setPrefab(new Hut());
        setPrefab(new GreenHouse());
        setPrefab(new Lake(8, 8, 40, 86));
        setPrefab(new Lake(12, 12, 64, 16));
        setPrefab(new Lake(25, 25, 30, 40));
        setPrefab(new Lake(20, 20, 56, 74));
    }

    @Override
    protected void spawnDynamicElements() {
        Random random = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == startingPoint.y() && x == startingPoint.x()) continue;

                Tile tile = grid[y][x];
                if (tile.getTerrainType() == TerrainType.DIRT) {
                    double chance = random.nextDouble();
                    if (chance < 0.1) {
                        addElement(PlantedTree.getMatureTree(TreeMetaData.getTree("Oak Tree")), y, x);
                    } else if (chance < 0.2) {
                        addElement(PlantedTree.getMatureTree(TreeMetaData.getTree("Pine Tree")), y, x);
                    } else if (chance < 0.25) {
                        addElement(MineralMetaData.getMineral("Rock"), y, x);
                    } else if (chance < 0.3) {
                        addElement(CollectableHolder.getRandomCollectable(SeasonName.SPRING), y, x);
                    }
                }
            }
        }
    }
}

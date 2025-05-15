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

public class StandardMap extends GameMap {
    public StandardMap() {
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
        setPrefab(new Lake(8, 8, 36, 86));
        setPrefab(new Lake(20, 30, 56, 34));
    }

    @Override
    protected void spawnDynamicElements() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == startingPoint.y() && x == startingPoint.x()) continue;

                Tile tile = grid[y][x];
                if (tile.getTerrainType() == TerrainType.DIRT) {
                    double chance = generator.nextDouble();
                    if (chance < 0.05) {
                        addElement(PlantedTree.getMatureTree(TreeMetaData.getTree("Oak Tree")), y, x);
                    } else if (chance < 0.1) {
                        addElement(PlantedTree.getMatureTree(TreeMetaData.getTree("Pine Tree")), y, x);
                    } else if (chance < 0.2) {
                        addElement(MineralMetaData.getMineral("Rock"), y, x);
                    } else if (chance < 0.25) {
                        addElement(CollectableHolder.getRandomCollectable(SeasonName.SPRING), y, x);
                    }
                }
            }
        }
    }
}

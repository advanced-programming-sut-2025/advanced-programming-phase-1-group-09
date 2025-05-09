package models.GameWorld.Map;

import models.DataManagers.TreeMetaData;
import models.GameWorld.Coordinate;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Farming.PlantedTree;
import models.GameWorld.Map.Elements.Collectable.CollectableManager;
import models.GameWorld.Map.Elements.Prefabs.GreenHouse;
import models.GameWorld.Map.Elements.Prefabs.Hut;
import models.GameWorld.Map.Elements.Prefabs.Lake;
import models.GameWorld.Map.Elements.Prefabs.Quarry;
import models.GameWorld.Map.Elements.Rock;

import java.util.Random;

public class StandardMap extends GameMap {
    public StandardMap() {
        super(80, 100, new Coordinate(20, 99));
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
        placeMultiTileElement(new Quarry());
        placeMultiTileElement(new Hut());
        placeMultiTileElement(new GreenHouse());

        Lake smallLake = new Lake(8, 8, 36, 86);
        placeMultiTileElement(smallLake);
        setLake(smallLake);

        Lake bigLake = new Lake(20, 30, 56, 34);
        placeMultiTileElement(bigLake);
        setLake(bigLake);
    }

    @Override
    protected void spawnDynamicElements() {
        Random random = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Tile tile = grid[y][x];
                if (tile.getTerrainType() == TerrainType.DIRT) {
                    double chance = random.nextDouble();
                    if (chance < 0.45) {
                        tile.addElement(PlantedTree.getMatureTree(TreeMetaData.getTree("Oak Tree")));
                    } else if (chance < 0.6) {
                        tile.addElement(PlantedTree.getMatureTree(TreeMetaData.getTree("Pine Tree")));
                    } else if (chance < 0.8) {
                        tile.addElement(new Rock());
                    } else if (chance < 0.9) {
                        tile.addElement(CollectableManager.getRandom(SeasonName.SPRING));
                    }
                }
            }
        }
    }
}

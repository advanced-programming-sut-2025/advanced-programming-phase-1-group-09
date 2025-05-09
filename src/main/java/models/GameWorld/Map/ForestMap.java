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

public class ForestMap extends GameMap {
    public ForestMap() {
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

        Lake smallLake1 = new Lake(8, 8, 40, 86);
        placeMultiTileElement(smallLake1);
        setLake(smallLake1);

        Lake smallLake2 = new Lake(12, 12, 64, 16);
        placeMultiTileElement(smallLake2);
        setLake(smallLake2);

        Lake bigLake1 = new Lake(25, 30, 35, 40);
        placeMultiTileElement(bigLake1);
        setLake(bigLake1);

        Lake bigLake2 = new Lake(20, 20, 56, 74);
        placeMultiTileElement(bigLake2);
        setLake(bigLake2);
    }

    @Override
    protected void spawnDynamicElements() {
        Random random = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Tile tile = grid[y][x];
                if (tile.getTerrainType() == TerrainType.DIRT) {
                    double chance = random.nextDouble();
                    if (chance < 0.5) {
                        tile.addElement(PlantedTree.getMatureTree(TreeMetaData.getTree("Oak Tree")));
                    } else if (chance < 0.7) {
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

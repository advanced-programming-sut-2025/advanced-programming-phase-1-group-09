package models.GameWorld.Map;

import models.DataManagers.MineralMetaData;
import models.DataManagers.TreeMetaData;
import models.GameWorld.Coordinate;
import models.GameWorld.Farming.PlantedTree;
import models.GameWorld.Map.Elements.Prefabs.Lake;
import models.GameWorld.Map.Elements.Prefabs.Shop.*;
import models.GameWorld.TimeState;

public class PublicMap extends GameMap {
    public PublicMap() {
        super(100, 120, new Coordinate(0, 0));
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
        setPrefab(new Lake(5, width, height - 5, 0));
        setPrefab(new Blacksmith());
        setPrefab(new JojaMart());
        setPrefab(new GeneralStore());
        setPrefab(new CarpenterShop());
        setPrefab(new FishShop());
        setPrefab(new MarnieRanch());
        setPrefab(new StardropSaloon());
    }

    @Override
    protected void spawnDynamicElements() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == startingPoint.y() && x == startingPoint.x()) continue;

                Tile tile = grid[y][x];
                if (tile.getTerrainType() == TerrainType.DIRT) {
                    double chance = generator.nextDouble();
                    if (chance < 0.03) {
                        addElement(PlantedTree.getMatureTree(TreeMetaData.getTree("Oak Tree")), y, x);
                    } else if (chance < 0.06) {
                        addElement(PlantedTree.getMatureTree(TreeMetaData.getTree("Pine Tree")), y, x);
                    } else if (chance < 0.1) {
                        addElement(MineralMetaData.getMineral("Rock"), y, x);
                    }
                }
            }
        }
    }

    @Override
    public void onTimeChange(TimeState newState) {

    }
}

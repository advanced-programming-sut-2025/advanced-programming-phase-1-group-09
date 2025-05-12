package models.GameWorld.Map;

import models.GameWorld.Coordinate;
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

    }

    @Override
    protected void spawnDynamicElements() {

    }

    @Override
    public void onTimeChange(TimeState newState) {

    }
}

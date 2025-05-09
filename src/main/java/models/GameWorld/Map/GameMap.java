package models.GameWorld.Map;

import models.GameWorld.Coordinate;
import models.GameWorld.Map.Elements.Prefabs.Lake;
import models.GameWorld.Map.Elements.MapElement;
import models.GameWorld.Map.Elements.MultiTileElement;

public abstract class GameMap {
    protected final int height;
    protected final int width;
    protected final Tile[][] grid;
    private final Coordinate startingPoint;

    public GameMap(int height, int width, Coordinate startingPoint) {
        this.height = height;
        this.width = width;
        this.grid = new Tile[height][width];
        this.startingPoint = startingPoint;
        initTiles();
        placeStaticElements();
        spawnDynamicElements();
    }

    protected abstract void initTiles();
    protected abstract void placeStaticElements();
    protected abstract void spawnDynamicElements();

    public int getHeight() { return height; }

    public int getWidth() { return width; }

    public boolean isCoordinateWithinMap(int y, int x) {
        return y >= 0 && y < height && x >= 0 && x < width;
    }

    public Tile getTile(int y, int x) {
        if (isCoordinateWithinMap(y, x)) return grid[y][x];
        return null;
    }

    public Coordinate getStartingPoint() { return startingPoint; }

    protected void placeMultiTileElement(MultiTileElement element) {
        if (element.getY() < 0 || element.getX() < 0 || element.getY() >= height || element.getX() >= width) return;

        for (int dy = 0; dy < element.getHeight(); dy++) {
            for (int dx = 0; dx < element.getWidth(); dx++) {
                Tile tile = grid[element.getY() + dy][element.getX() + dx];
                tile.addElement(element);
            }
        }
    }

    protected void setLake(Lake lake) {
        for (int y = lake.getY(); y < lake.getY() + lake.getHeight(); y++) {
            for (int x = lake.getX(); x < lake.getX() + lake.getWidth(); x++) {
                grid[y][x].setTerrainType(TerrainType.WATER);
            }
        }
    }

    public void addElement(MapElement element, int y, int x) {
        if (getTile(y, x) == null) return;
        getTile(y, x).addElement(element);
        if (element.isFixed()) {
            getTile(y, x).setTerrainType(TerrainType.RESERVED);
        }
    }
}

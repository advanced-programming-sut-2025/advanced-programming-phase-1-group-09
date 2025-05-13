package models.GameWorld.Map;

import models.DataManagers.MineralMetaData;
import models.DataManagers.TreeMetaData;
import models.GameWorld.Coordinate;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Enums.WeatherType;
import models.GameWorld.Farming.PlantedTree;
import models.GameWorld.Map.Elements.Collectable.CollectableHolder;
import models.GameWorld.Map.Elements.Prefabs.Lake;
import models.GameWorld.Map.Elements.MapElement;
import models.GameWorld.Map.Elements.MultiTileElement;
import models.GameWorld.Map.Elements.Prefabs.Prefab;
import models.GameWorld.TimeState;
import models.Result;
import models.TimeObserver;

import java.util.*;

public abstract class GameMap implements TimeObserver {
    protected static final Random generator = new Random();

    protected final int height;
    protected final int width;
    protected final Tile[][] grid;
    protected final Coordinate startingPoint;
    protected final HashMap<String, Prefab> prefabs;

    public GameMap(int height, int width, Coordinate startingPoint) {
        this.height = height;
        this.width = width;
        this.grid = new Tile[height][width];
        this.startingPoint = startingPoint;
        this.prefabs = new HashMap<>();

        initTiles();
        placeStaticElements();
        spawnDynamicElements();

        getTile(startingPoint).setWalkable(true);
    }

    protected abstract void initTiles();
    protected abstract void placeStaticElements();
    protected abstract void spawnDynamicElements();

    protected void setPrefab(Prefab prefab) {
        for (int y = prefab.getY(); y < prefab.getY() + prefab.getHeight(); y++) {
            for (int x = prefab.getX(); x < prefab.getX() + prefab.getWidth(); x++) {
                Tile tile = grid[y][x];

                switch (prefab.getName()) {
                    case "Lake" -> tile.setTerrainType(TerrainType.LAKE);
                    case "Quarry" -> tile.setTerrainType(TerrainType.QUARRY);
                    case "Hut" -> tile.setTerrainType(TerrainType.HUT);
                    case "GreenHouse" -> tile.setTerrainType(TerrainType.GREENHOUSE);
                }

                tile.setWalkable(false);
                prefabs.put(prefab.getName(), prefab);
            }
        }
    }

    protected void placeMultiTileElement(MultiTileElement element) {
        if (element.getY() < 0 || element.getX() < 0 ||
                element.getY() + element.getHeight() >= height ||
                element.getX() + element.getWidth()  >= width) return;

        for (int dy = 0; dy < element.getHeight(); dy++) {
            for (int dx = 0; dx < element.getWidth(); dx++) {
                addElement(element, element.getY() + dy, element.getX() + dx);
            }
        }
    }

    protected void spawn(int y, int x, SeasonName season) {
        Tile tile = grid[y][x];
        if (!tile.getElements().isEmpty()) return;

        if (generator.nextDouble() < 0.01) {
            switch (tile.getTerrainType()) {
                case PLOWED_DIRT -> tile.addElement(CollectableHolder.getRandomSeed(season));
                case DIRT -> tile.addElement(CollectableHolder.getRandomForagingCrop(season));
                case QUARRY -> tile.addElement(MineralMetaData.getRandomMineral());
            }
        }
    }

    public int getHeight() { return height; }

    public int getWidth() { return width; }

    public boolean isCoordinateWithinMap(int y, int x) {
        return y >= 0 && y < height && x >= 0 && x < width;
    }
    public boolean isCoordinateWithinMap(Coordinate c) {
        return isCoordinateWithinMap(c.y(), c.x());
    }

    public Tile getTile(int y, int x) {
        if (isCoordinateWithinMap(y, x)) return grid[y][x];
        return null;
    }
    public Tile getTile(Coordinate position) {
        return getTile(position.y(), position.x());
    }

    public Coordinate getStartingPoint() { return startingPoint; }

    public void addElement(MapElement element, int y, int x) {
        if (!isCoordinateWithinMap(y, x)) return;
        grid[y][x].addElement(element);
    }

    public Prefab getPrefab(String name) {
        return prefabs.get(name);
    }

    @Override
    public void onTimeChange(TimeState newState) {
        // Spawn new elements
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == startingPoint.y() && x == startingPoint.x()) continue;
                if (grid[y][x].getElements().isEmpty()) {
                    spawn(y, x, newState.getSeason().getCurrentSeason());
                }
            }
        }

        // Thunder
        if (newState.getWeather().getCurrentWeather() == WeatherType.STORM) {
            Set<Coordinate> positionsToBurn = new HashSet<>();
            while (positionsToBurn.size() < 4) {
                int number = generator.nextInt(width * height);
                Coordinate position = new Coordinate(number / width, number % width);
                positionsToBurn.add(position);
            }
            for (Coordinate position : positionsToBurn) {
                thunder(this, position.y(), position.x());
            }
        }
    }

    public static Result thunder(GameMap map, int y, int x) {
        if (!map.isCoordinateWithinMap(y, x)) return new Result(false, "Target out of bounds!");
        Tile tile = map.getTile(y, x);

        String result = "";

        ArrayList<MapElement> newElements = new ArrayList<>();
        for (MapElement element : tile.getElements()) {
            if (element instanceof PlantedTree tree) {
                // Add coal
                newElements.add(MineralMetaData.getMineral("Coal").extract());

                // Add seed
                newElements.add(TreeMetaData.getSeed(tree.getTreeDefinition().getSource()));
            }
            result += element.getName() + ", ";
        }
        tile.getElements().clear();
        tile.getElements().addAll(newElements);

        result = result.isEmpty() ?
                "No elements were destroyed" :
                result.substring(0, result.length() - 2) + " destroyed";
        return new Result(true, result);
    }
}

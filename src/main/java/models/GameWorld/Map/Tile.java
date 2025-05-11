package models.GameWorld.Map;

import models.GameWorld.Farming.Planted;
import models.GameWorld.Map.Elements.Collectable.Collectable;
import models.GameWorld.Map.Elements.MapElement;
import views.ConsoleColors;

import java.util.ArrayList;

public class Tile {
    private TerrainType terrainType;
    private final ArrayList<MapElement> elements;
    private boolean isWalkable;

    public Tile(TerrainType terrainType) {
        this.terrainType = terrainType;
        elements = new ArrayList<>();
        isWalkable = true;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType) {
        this.terrainType = terrainType;
    }

    public ArrayList<MapElement> getElements() {
        return elements;
    }

    public ArrayList<MapElement> getInteractableElements() {
        ArrayList<MapElement> interactableElements = new ArrayList<>();
        for (MapElement element : elements) {
            if (element.isInteractable()) {
                interactableElements.add(element);
            }
        }
        return interactableElements;
    }

    public void addElement(MapElement element) {
        elements.add(element);
        if (!(element instanceof Collectable)) {
            isWalkable = false;
        }
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }

    public void water() {
        for (MapElement element : elements) {
            if (element instanceof Planted planted) {
                planted.setWateredToday(true);
            }
        }
    }

    public String getDisplaySymbol() {
        if (!elements.isEmpty()) {
            MapElement top = elements.get(elements.size() - 1);
            return top.getSymbol();
        }

        return ConsoleColors.YELLOW_BACKGROUND + " ";
    }
}

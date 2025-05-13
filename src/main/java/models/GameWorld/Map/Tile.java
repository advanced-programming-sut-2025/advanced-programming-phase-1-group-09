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

    public void removeElement(MapElement element) {
        elements.remove(element);
        for (MapElement e : elements) {
            if (!(e instanceof Collectable)) {
                isWalkable = false;
                return;
            }
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
        return switch (terrainType) {
            case GREENHOUSE -> ConsoleColors.GREEN_BACKGROUND +  "#";
            case HUT -> ConsoleColors.WHITE_BACKGROUND + ConsoleColors.BLACK_BOLD +  "H";
            case LAKE -> ConsoleColors.BLUE_BACKGROUND_BRIGHT +  "L";
            case QUARRY -> ConsoleColors.BLACK_BACKGROUND +  "Q";
            default -> elements.isEmpty() ? (ConsoleColors.YELLOW_BACKGROUND + " ") : (elements.getLast().getSymbol());
        };
    }
}

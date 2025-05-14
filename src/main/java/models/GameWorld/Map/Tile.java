package models.GameWorld.Map;

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

    public String getDisplaySymbol() {
        return switch (terrainType) {
            case GREENHOUSE -> ConsoleColors.GREEN_BACKGROUND +  "#";
            case HUT -> ConsoleColors.WHITE_BACKGROUND + ConsoleColors.BLACK_BOLD +  "H";
            case LAKE -> ConsoleColors.BLUE_BACKGROUND_BRIGHT +  "L";
            case QUARRY -> ConsoleColors.BLACK_BACKGROUND +  "Q";
            case BLACKSMITH -> ConsoleColors.BLACK_BACKGROUND + "B";
            case JOJA_MART -> ConsoleColors.BLUE_BACKGROUND + "J";
            case GENERAL_STORE -> ConsoleColors.YELLOW_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + "P";
            case CARPENTER_SHOP -> ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "C";
            case FISH_SHOP -> ConsoleColors.GREEN_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + "F";
            case MARNIE_RANCH -> ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + "M";
            case STARDROP_SALOON -> ConsoleColors.YELLOW_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + "S";
            case PLOWED_DIRT -> ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + "▒";
            default -> elements.isEmpty() ? (ConsoleColors.YELLOW_BACKGROUND + " ") : (elements.getLast().getSymbol());
        };
    }
}

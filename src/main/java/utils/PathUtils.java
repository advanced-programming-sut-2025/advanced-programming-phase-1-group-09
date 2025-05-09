package utils;

import models.GameWorld.Coordinate;
import models.GameWorld.Enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class PathUtils {
    public static List<Direction> calculateDirections(List<Coordinate> path) {
        List<Direction> dirs = new ArrayList<>();
        for (int i = 1; i < path.size(); i++) {
            int dx = path.get(i).x() - path.get(i - 1).x();
            int dy = path.get(i).y() - path.get(i - 1).y();
            dirs.add(Direction.getDirection(dx, dy));
        }
        return dirs;
    }

    public static int countTurns(List<Direction> dirs) {
        int turns = 0;
        for (int i = 1; i < dirs.size(); i++) {
            if (dirs.get(i) != dirs.get(i - 1)) turns++;
        }
        return turns;
    }
}

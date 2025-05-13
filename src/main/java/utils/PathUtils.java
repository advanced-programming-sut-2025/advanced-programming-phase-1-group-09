package utils;

import models.GameWorld.Coordinate;
import models.GameWorld.Enums.Direction;
import models.GameWorld.Map.GameMap;
import models.GameWorld.Map.Tile;

import java.util.*;

public class PathUtils {
    private static class Node implements Comparable<Node> {
        Coordinate coordinate;
        Node parent;
        int g; // cost from the start
        int h; // heuristic to goal

        Node(Coordinate coordinate, Node parent, int g, int h) {
            this.coordinate = coordinate;
            this.parent = parent;
            this.g = g;
            this.h = h;
        }

        int f() {
            return g + h;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f(), other.f());
        }
    }

    public static List<Coordinate> findPath(GameMap map, Coordinate start, Coordinate goal) {
        if (!map.isCoordinateWithinMap(start) || !map.isCoordinateWithinMap(goal)) {
            return null;
        }

        if (!map.getTile(start).isWalkable() || !map.getTile(goal).isWalkable()) {
            return null;
        }

        boolean[][] closed = new boolean[map.getHeight()][map.getWidth()];
        Map<Coordinate, Integer> gScores = new HashMap<>();
        PriorityQueue<Node> open = new PriorityQueue<>();

        gScores.put(start, 0);
        open.add(new Node(start, null, 0, heuristic(start, goal)));

        while (!open.isEmpty()) {
            Node current = open.poll();
            Coordinate pos = current.coordinate;

            if (pos.equals(goal)) {
                List<Coordinate> path = new ArrayList<>();
                while (current != null) {
                    path.add(current.coordinate);
                    current = current.parent;
                }
                Collections.reverse(path);
                return path;
            }

            if (closed[pos.y()][pos.x()]) continue;
            closed[pos.y()][pos.x()] = true;

            for (Direction d : Direction.values()) {
                int ny = pos.y() + d.dy;
                int nx = pos.x() + d.dx;

                if (!map.isCoordinateWithinMap(ny, nx)) continue;
                if (closed[ny][nx]) continue;

                Tile tile = map.getTile(ny, nx);
                if (tile == null || !tile.isWalkable()) continue;

                Coordinate neighbor = new Coordinate(ny, nx);  // Note the order: y, x
                int tentativeG = current.g + 1;

                if (!gScores.containsKey(neighbor) || tentativeG < gScores.get(neighbor)) {
                    gScores.put(neighbor, tentativeG);
                    int h = heuristic(neighbor, goal);
                    open.add(new Node(neighbor, current, tentativeG, h));
                }
            }
        }

        return null;
    }

    private static int heuristic(Coordinate a, Coordinate b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

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

    public static int calculateEnergy(List<Coordinate> path) {
        List<Direction> dirs = calculateDirections(path);
        int turns = countTurns(dirs);
        int tiles = path.size() - 1;

        return (tiles + 10 * turns) / 20;
    }
}

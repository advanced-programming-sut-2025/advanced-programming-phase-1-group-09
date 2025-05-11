package utils;

import models.GameWorld.Coordinate;
import models.GameWorld.Enums.Direction;
import models.GameWorld.Map.GameMap;
import models.GameWorld.Map.TerrainType;
import models.GameWorld.Map.Tile;

import java.util.*;

public class PathFinder {
    private static class Node implements Comparable<Node> {
        Coordinate coordinate;
        Node parent;
        int g; // cost from start
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

    public static List<Coordinate> findPathAStar(GameMap map, Coordinate start, Coordinate goal) {
        System.out.println("=== A* ===");
        System.out.println("Start: " + start);
        System.out.println("Goal: " + goal);
        System.out.println("Start valid: " + map.isCoordinateWithinMap(start.y(), start.x()));
        System.out.println("Goal valid: " + map.isCoordinateWithinMap(goal.y(), goal.x()));
        System.out.println("Start tile walkable: " + map.getTile(start.y(), start.x()).isWalkable());
        System.out.println("Goal tile walkable: " + map.getTile(goal.y(), goal.x()).isWalkable());

        boolean[][] closed = new boolean[map.getHeight()][map.getWidth()];
        Map<Coordinate, Integer> gScores = new HashMap<>();
        PriorityQueue<Node> open = new PriorityQueue<>();

        gScores.put(start, 0);
        open.add(new Node(start, null, 0, heuristic(start, goal)));

        while (!open.isEmpty()) {
            Node current = open.poll();
            Coordinate pos = current.coordinate;

            if (!map.isCoordinateWithinMap(pos.y(), pos.x())) continue;
            if (closed[pos.y()][pos.x()]) continue;

            closed[pos.y()][pos.x()] = true;

            if (pos.equals(goal)) {
                List<Coordinate> path = new ArrayList<>();
                while (current != null) {
                    path.add(current.coordinate);
                    current = current.parent;
                }
                Collections.reverse(path);
                return path;
            }

            for (Direction d : Direction.values()) {
                int nx = pos.x() + d.dx;
                int ny = pos.y() + d.dy;

                if (!map.isCoordinateWithinMap(ny, nx)) continue;
                if (closed[ny][nx]) continue;

                Tile tile = map.getTile(ny, nx);
                if (tile == null || !tile.isWalkable()) continue;

                Coordinate neighbor = new Coordinate(nx, ny);
                int tentativeG = current.g + 1;

                if (!gScores.containsKey(neighbor) || tentativeG < gScores.get(neighbor)) {
                    gScores.put(neighbor, tentativeG);
                    int h = heuristic(neighbor, goal);
                    open.add(new Node(neighbor, current, tentativeG, h));
                }
            }
        }

        return null; // no path found
    }

    public static List<Coordinate> findPathBFS(GameMap map, Coordinate start, Coordinate end) {
        int h = map.getHeight(), w = map.getWidth();
        boolean[][] visited = new boolean[h][w];
        Map<Coordinate, Coordinate> cameFrom = new HashMap<>();
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(start);
        visited[start.y()][start.x()] = true;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (current.equals(end)) break;

            for (Direction d : Direction.values()) {
                int nx = current.x() + d.dx;
                int ny = current.y() + d.dy;

                if (nx < 0 || ny < 0 || nx >= w || ny >= h) continue;
                if (visited[ny][nx]) continue;

                Tile tile = map.getTile(ny, nx);
                if (tile == null || tile.getTerrainType() == TerrainType.WATER
                        || tile.getTerrainType() == TerrainType.RESERVED)
                    continue;

                Coordinate next = new Coordinate(nx, ny);
                queue.add(next);
                visited[ny][nx] = true;
                cameFrom.put(next, current);
            }
        }

        if (!cameFrom.containsKey(end)) return null;

        List<Coordinate> path = new ArrayList<>();
        for (Coordinate at = end; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    private static int heuristic(Coordinate a, Coordinate b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}

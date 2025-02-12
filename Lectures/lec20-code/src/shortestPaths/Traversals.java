package shortestPaths;

import java.util.*;
import java.util.function.Consumer;

public class Traversals {
    /**
     * Traverse all vertices reachable from `start` in a breadth-first-search order, performing
     * `preAction.accept()` on each one as it is visited.
     */
    public static void bfsWalk(Vertex start, Consumer<String> preAction) {
        // Set of all vertex labels that have been discovered.
        Set<String> discovered = new HashSet<>();

        // Queue of discovered vertices that have not yet been visited (FIFO in discovery order).
        Queue<Vertex> frontier = new LinkedList<>();

        // Discover `start`.
        discovered.add(start.label());
        frontier.add(start);

        // Visit vertices in discovery order until all reachable vertices have been visited.
        while (!frontier.isEmpty()) {
            Vertex v = frontier.remove();
            // "Visit" `v` by discovering all neighbors.
            preAction.accept(v.label());
            for (Edge e : v.outgoingEdges()) {
                Vertex neighbor = e.destination();
                // A vertex can only be discovered once.
                if (!discovered.contains(neighbor.label())) {
                    discovered.add(neighbor.label());
                    frontier.add(neighbor);
                }
            }
            // `v` is now "settled" (all neighbors have been discovered).
        }
    }

    /**
     * Compute the minimum path length from `start` to all vertices reachable from it.  The returned
     * map associates each reachable vertex's label with its distance from `start`, and iterating
     * over the map will yield vertex labels in ascending order of distance (that is, in a
     * breadth-first search order).  Here, path "length" and "distance" refer to the number of edges
     * (edge weights are ignored).
     */
    public static Map<String, Integer> bfsDistances(Vertex start) {
        // Map of vertex labels to distance from `start`.
        // Use LinkedHashMap to preserve discovery order in return value.
        Map<String, Integer> layers = new LinkedHashMap<>();

        // Queue of discovered vertices that have not yet been visited (FIFO in discovery order).
        Queue<Vertex> frontier = new LinkedList<>();

        // Discover `start`.
        layers.put(start.label(), 0);
        frontier.add(start);

        // Visit vertices in discovery order until all reachable vertices have been visited.
        while (!frontier.isEmpty()) {
            Vertex v = frontier.remove();
            int vDist = layers.get(v.label());

            // "Visit" `v` by discovering all neighbors.
            for (Edge e : v.outgoingEdges()) {
                Vertex neighbor = e.destination();
                // Neighbor is undiscovered if it has no distance yet
                if (!layers.containsKey(neighbor.label())) {
                    // Since `neighbor` was not discovered earlier (and BFS discovers nodes in order
                    // of their distance from `start`), the shortest path from `start` to `neighbor`
                    // must be the shortest path to `v` plus the edge from `v` to `neighbor`.
                    layers.put(neighbor.label(), vDist + 1);
                    frontier.add(neighbor);
                }
            }
        }
        return layers;
    }
}

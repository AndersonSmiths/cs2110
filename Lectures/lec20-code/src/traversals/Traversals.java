package traversals;

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
        Map<String, Integer> dists = new LinkedHashMap<>();

        // Queue of discovered vertices that have not yet been visited (FIFO in discovery order).
        Queue<Vertex> frontier = new LinkedList<>();

        // Discover `start`.
        dists.put(start.label(), 0);
        frontier.add(start);

        // Visit vertices in discovery order until all reachable vertices have been visited.
        while (!frontier.isEmpty()) {
            Vertex v = frontier.remove();
            int vDist = dists.get(v.label());

            // "Visit" `v` by discovering all neighbors.
            for (Edge e : v.outgoingEdges()) {
                Vertex neighbor = e.destination();
                // Neighbor is undiscovered if it has no distance yet
                if (!dists.containsKey(neighbor.label())) {
                    // Since `neighbor` was not discovered earlier (and BFS discovers nodes in order
                    // of their distance from `start`), the shortest path from `start` to `neighbor`
                    // must be the shortest path to `v` plus the edge from `v` to `neighbor`.
                    dists.put(neighbor.label(), vDist + 1);
                    frontier.add(neighbor);
                }
            }
        }
        return dists;
    }

    /**
     * Traverse all vertices reachable from `start` in a depth-first-search order, performing
     * `preAction.accept()` on each one as it is visited and `postAction.accept()` on each one as it
     * is settled.
     */
    public static void dfsWalk(Vertex start, Consumer<String> preAction,
                               Consumer<String> postAction) {
        // Set of all vertex labels that have been discovered.
        Set<String> discovered = new HashSet<>();
        discovered.add(start.label());
        dfsVisit(start, preAction, postAction, discovered);
    }

    /**
     * Traverse all undiscovered vertices reachable from `start` in a depth-first-search order,
     * performing `preAction.accept()` on each one as it is visited and `postAction.accept()` on
     * each one as it is settled.  `discovered` must contain labels of already-discovered vertices
     * and will be added to as new vertices are discovered.
     */
    private static void dfsVisit(Vertex v, Consumer<String> preAction, Consumer<String> postAction,
                                 Set<String> discovered) {

        // "Visit" `v` by discovering all neighbors.
        preAction.accept(v.label());
//         for each successor of `v`
        for (Edge edge : v.outgoingEdges()) {
            Vertex successor = edge.destination();
//            if successor is undiscovered
            if (!discovered.contains(successor.label())) {
//                visit successor (recursive call)
                discovered.add(successor.label());
                dfsVisit(successor, preAction, postAction, discovered);
            }
        }
        // `v` is now "settled" (all neighbors have been discovered).
        postAction.accept(v.label());

    }

    /**
     * Return a topological ordering of the vertices in `graph`. Throws IllegalArgumentException if
     * graph is cyclic.
     */
    public static List<String> topologicalOrderWalk(Graph graph) {
        // Allocate list for assembling result
        List<String> ans = new LinkedList<>();

        // Create data structure for storing vertex information
        Set<String> discovered = new HashSet<>();
        Set<String> settled = new HashSet<>();

//         For each vertex in the graph:
        for (Vertex v : graph.vertices()) {
//                 If v is undiscovered:
            if (!discovered.contains(v.label())) {
//                         Visit v by calling topologicalOrderVisit() (note: this checks for cycles for us)
                topologicalOrderVisit(v, ans, discovered, settled);
            }
        }

        return ans;
    }

    /**
     * Perform a depth-first search starting at `start`, prepending each vertex visited to `order`
     * after fully processing it. `discovered` contains discovered vertex labels,
     * and `settled` contains settled vertex labels.
     * Throws IllegalArgumentException if subgraph reachable from `start` is cyclic.
     */
    private static void topologicalOrderVisit(Vertex v, List<String> order, Set<String> discovered,
                                              Set<String> settled) {
        // TODO: There is a bug in this implementation. Find the bug via testing and fix it!

//         for each successor of `v`
        for (Edge edge : v.outgoingEdges()) {
            Vertex successor = edge.destination();
//            if successor is undiscovered
            if (!discovered.contains(successor.label())) {
                discovered.add(v.label());
//                visit successor (recursive call)
                topologicalOrderVisit(successor, order, discovered, settled);
            } else if (!settled.contains(successor.label())) {
//                a successor that is already discovered but not settled. A cycle!
                throw new IllegalArgumentException("Graph is cyclic!");
            }
        }
        // `v` is now "settled" (all neighbors have been discovered).
        order.addFirst(v.label());
        settled.add(v.label());
    }


    /**
     * Return a sequence of all vertex labels in `g` in topological order if `g` is acyclic.  Throws
     * IllegalArgumentException if `g` is cyclic.  Topological order guarantees that there is no
     * path from a node in the sequence to an earlier node in the sequence.
     */
    public static List<String> topologicalOrderKahns(Graph g) {
        // This is an implementation of Kahn's algorithm, which identifies vertices that have no
        // predecessors aside from those that have already been added to the answer.  It constructs
        // the topological ordering in forward order.

        // The topological ordering identified so far.  If a vertex is in this list, then all of its
        // predecessors must appear earlier in the list.
        LinkedList<String> order = new LinkedList<>();

        // Determine initial in-degree of all vertices and add "source" vertices to output
        Map<String, Integer> inDegrees = new HashMap<>();
        // Initialize in-degrees to 0
        for (Vertex v : g.vertices()) {
            inDegrees.put(v.label(), 0);
        }
        // Accumulate in-degrees
        for (Vertex v : g.vertices()) {
            for (Edge e : v.outgoingEdges()) {
                String dstLabel = e.destination().label();
                inDegrees.put(dstLabel, inDegrees.get(dstLabel) + 1);
            }
        }
        // Identify candidate starting nodes
        for (Map.Entry<String, Integer> entry : inDegrees.entrySet()) {
            if (entry.getValue() == 0) {
                order.add(entry.getKey());
            }
        }

        // Iterate over the order so far.  Any nodes for which `v` was the last outstanding
        // predecessor are inserted into the order immediately after `v`,
        ListIterator<String> it = order.listIterator();
        while (it.hasNext()) {
            Vertex v = g.getVertex(it.next());
            // Subtract 1 from in-degree of all successor nodes
            for (Edge e : v.outgoingEdges()) {
                String successorLabel = e.destination().label();
                inDegrees.put(successorLabel, inDegrees.get(successorLabel) - 1);
                // All predecessors have been iterated over; insert this node
                if (inDegrees.get(successorLabel) == 0) {
                    it.add(successorLabel);
                    // With ListIterator, must back up over newly inserted element in order to
                    // iterate over it with `next()`.
                    it.previous();
                }
            }
        }

        // Confirm that all vertices are in `order`; if not, the remaining vertices must be part of
        // one or more cycles.
        if (order.size() < g.vertexCount()) {
            throw new IllegalArgumentException("Graph is cyclic");
        }

        return order;
    }
}
package shortestPaths;

import java.io.IOException;
import java.util.*;

/** Demo for unweighted single-source shortest paths. */
public class BfsDemo {

    /**
     * Perform a breadth-first traversal of the graph reachable from `start` and return information
     * about the shortest unweighted paths from `start` to every vertex, including its length
     * (number of edges, aka layer number) and the label of the penultimate vertex on the path.
     */
    static Map<String, PathInfo> bfsPaths(Vertex start) {
        // Associate vertex labels with info about the shortest unweighted path from `start` to that
        // vertex.  Populated as vertices are discovered (in BFS order).
        Map<String, PathInfo> pathInfo = new LinkedHashMap<>();

        // For BFS, the frontier is a FIFO queue.  It is populated in BFS discovery order, so it is
        // guaranteed to be sorted by layer number (and to only contain vertices in layers N and N+1
        // for some N).
        Queue<Vertex> frontier = new LinkedList<>();

        // Enqueue starting vertex (which counts as layer 0).
        pathInfo.put(start.label(), new PathInfo(0, null));
        frontier.add(start);

        while (!frontier.isEmpty()) {
            // Remove the next vertex in the frontier.  Thanks to BFS ordering, it is guaranteed to
            // be in the smallest layer of any vertex in the frontier.
            Vertex v = frontier.remove();

            // Explore all neighbors to see if we discover any new ones.
            for (Edge e : v.outgoingEdges()) {
                Vertex neighbor = e.destination();

                // Only update path info if neighbor has not already been discovered.
                if (!pathInfo.containsKey(neighbor.label())) {
                    int layer = pathInfo.get(v.label()).layer + 1;
                    pathInfo.put(neighbor.label(), new PathInfo(layer, v.label()));
                    frontier.add(neighbor);
                }
            }
        }
        return pathInfo;
    }

    /**
     * Auxiliary vertex properties relevant to unweighted shortest paths.
     */
    static class PathInfo {
        /**
         * The length (number of edges) of the shortest path from a starting vertex to the vertex
         * associated with this info.  AKA "BFS layer".
         */
        int layer;

        /**
         * The label of the previous ("predecessor") vertex along the shortest path from a starting
         * vertex to the vertex associated with this info.  Null if the associated vertex is the
         * starting vertex.
         */
        String pred;

        public PathInfo(int layer, String predecessorLabel) {
            this.layer = layer;
            this.pred = predecessorLabel;
        }

        @Override
        public String toString() {
            return "{layer=" + layer + ", pred=" + pred + "}";
        }
    }

    /**
     * Print information about unweighted shortest paths between cities along rail lines.
     */
    public static void main(String[] args) throws IOException {
        // Read graph from file
        Graph connections = OopGraph.readDot("ticket_to_ride.dot");

        // Perform breadth-first search starting from the vertex representing Los Angeles
        Vertex start = connections.getVertex("Los Angeles");
        Map<String, PathInfo> paths = bfsPaths(start);

        // Print lengths and backpointers for shortest paths to all reachable nodes
        System.out.println("Shortest path info, starting at LA:");
        //System.out.println(paths);
        System.out.println(paths.toString().replace("},", "},\n"));
        System.out.println();

        // Print shortest path from Los Angeles to Washington, DC
        System.out.println("The number of edges in the shortest path from LA to DC is: " +
                paths.get("Washington").layer);
        System.out.println("The shortest (unweighted) path from LA to DC is: " +
                pathTo(paths, "Washington"));
    }

    /**
     * Return the sequence of vertex labels along the shortest path from a starting vertex to the
     * vertex with label "end".  `pathInfo` must contain backpointers for the shortest paths from
     * the starting vertex to all reachable vertices.
     */
    static List<String> pathTo(Map<String, PathInfo> pathInfo, String end) {
        // TODO: exercise - implement this!
        throw new UnsupportedOperationException();
    }
}

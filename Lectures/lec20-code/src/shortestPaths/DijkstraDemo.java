package shortestPaths;

import java.io.*;
import java.util.*;

/** Demo for weighted single-source shortest paths. */
public class DijkstraDemo {

    /**
     * Determine the minimum-weight paths from `start` to every vertex reachable from it.
     * Returns an association between destination vertex labels and info about a shortest path
     * to them (may not be unique).  Requires that all edge weights reachable from `start` are
     * non-negative.
     */
    static Map<String, PathEnd> shortestPaths(Vertex start) {
        // Associate vertex labels with info about the shortest-known path from `start` to that
        // vertex.  Populated as vertices are discovered (not as they are settled).
        Map<String, PathEnd> pathInfo = new LinkedHashMap<>();

        PQueue<Vertex> frontier = new PQueue<>();
        // TODO: implement Dijkstra's algorithm.



        // Notes: similar to BFS but...
        // track distance (weight) instead of layer
        // Use priority queue instead of FIFO queue for frontier (always remove node with smallest candidate path)
        return pathInfo;
    }

    // Note: The following helper classes for representing path info are defined as "records", which
    // is a Java feature we have not discussed in class.  Records are simply a shorthand for
    // creating a class that collects a few fields together; it automatically declares fields,
    // constructors, and observers in the "obvious" way and even generates `toString()`, `equals()`,
    // and `hashCode()` methods.  When you're just collecting named values together and don't need
    // to enforce class invariants or support complicated operations, records are a convenient
    // choice.

    /**
     * Pairs an Edge with its source vertex, allowing paths to be traced backwards.
     * `edge` is null when `source` is the vertex at the start of the path.
     */
    record Backpointer(Vertex source, Edge edge) { }

    /**
     * Represents a path ending at `link.edge.destination()` along with its total weight (distance).
     */
    record PathEnd(double distance, Backpointer link) { }


    public static void main(String[] args) throws IOException {
        Graph graph = OopGraph.readDot("ticket_to_ride.dot");
        //System.out.println(graph);

        var paths = shortestPaths(graph.getVertex("Los Angeles"));
        //System.out.println(paths);
        for (Map.Entry<String, PathEnd> entry : paths.entrySet()) {
            var dst = entry.getKey();
            var end = entry.getValue();
            System.out.printf("%15s  %2.0f  %s\n", dst, end.distance(),
                    (end.link() == null) ? "" : end.link().source().label());
        }
    }
}

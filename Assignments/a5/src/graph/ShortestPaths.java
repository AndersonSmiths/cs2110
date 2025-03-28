package graph;

import datastructures.HeapPQueue;
import datastructures.PQueue;
import datastructures.SlowPQueue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This object computes and remembers shortest paths through a weighted, directed graph with
 * nonnegative weights. Once shortest paths are computed from a specified source vertex, it allows
 * querying the distance to arbitrary vertices and the best paths to arbitrary destination
 * vertices.
 * <p>
 * Types Vertex and Edge are parameters, so their operations are supplied by a model object supplied
 * to the constructor.
 */
public class ShortestPaths<Vertex, Edge> {

    /**
     * The model for treating types Vertex and Edge as forming a weighted directed graph.
     */
    private final WeightedDigraph<Vertex, Edge> graph;

    /**
     * The distance to each vertex from the source.
     */
    private Map<Vertex, Double> distances;

    /**
     * The incoming edge for the best path to each vertex from the source vertex.
     */
    private Map<Vertex, Edge> bestEdges;

    /**
     * Creates: a single-source shortest-path finder for a weighted graph.
     *
     * @param graph The model that supplies all graph operations.
     */
    public ShortestPaths(WeightedDigraph<Vertex, Edge> graph) {
        this.graph = graph;
    }

    /**
     * Effect: Computes the best paths from a given source vertex, which can then be queried using
     * bestPath().
     */
    public void singleSourceDistances(Vertex source) {
        // Implementation constraint: use Dijkstra's single-source shortest paths algorithm.
        PQueue<Vertex> frontier = new HeapPQueue<>();
        distances = new HashMap<>();
        bestEdges = new HashMap<>();
        // source distance of 0 from self
        // add frontier, 0 priority
        distances.put(source, 0.0);
        frontier.add(source, 0.0);

        // inv: frontier is not empty
        while (!frontier.isEmpty()) {
            // removing minimum
            Vertex min = frontier.extractMin();
            double minDist = distances.get(min);
            // checking edges of the minDist vertex
            for (Edge edge : graph.outgoingEdges(min)) {
                Vertex vert = graph.dest(edge);
                double weight = graph.weight(edge);
                double distanceVerts = minDist + weight;

                // checking for shorter distances
                // ensures map.get(Object) is not null
                if (!distances.containsKey(vert) || distanceVerts < distances.get(vert)) {
                    distances.put(vert, distanceVerts);
                    bestEdges.put(vert, edge);

                    // changing priority if already in frontier
                    // if not, then add to frontier
                    try {
                        frontier.add(vert, distanceVerts);
                    } catch (IllegalArgumentException e){
                        frontier.changePriority(vert, distanceVerts);
                    }
                }

            }

        }
    }

    /**
     * Returns: the distance from the source vertex to the given vertex. Requires: distances have
     * been computed from a source vertex, and vertex v is reachable from that vertex.
     */
    public double getDistance(Vertex v) {
        assert !distances.isEmpty() : "Must run singleSourceDistances() first";
        Double d = distances.get(v);
        assert d != null : "v not reachable from source";
        return d;
    }

    /**
     * Returns: the best path from the source vertex to a given target vertex. The path is
     * represented as a list of edges. Requires: singleSourceDistances() has already been used to
     * compute best paths, and vertex target is reachable from that source.
     */
    public List<Edge> bestPath(Vertex target) {
        assert !bestEdges.isEmpty() : "Must run singleSourceDistances() first";
        LinkedList<Edge> path = new LinkedList<>();
        Vertex v = target;
        while (true) {
            Edge e = bestEdges.get(v);
            if (e == null) {
                break; // must be the source vertex (assuming target is reachable)
            }
            path.addFirst(e);
            v = graph.source(e);
        }
        return path;
    }
}

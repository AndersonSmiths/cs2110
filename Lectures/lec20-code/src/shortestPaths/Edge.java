package shortestPaths;

/**
 * Represents a weighted directed edge in a graph.
 */
public class Edge {
    /**
     * The vertex that this edge connects to.
     */
    private final Vertex destination;

    /**
     * The weight of this edge.
     */
    private final double weight;

    /**
     * Create a new Edge with weight `weight` connecting to `destination`.
     */
    public Edge(Vertex destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * Return the vertex that this edge connects to.
     */
    public Vertex destination() {
        return destination;
    }

    /**
     * Return the weight of this edge.
     */
    public double weight() {
        return weight;
    }
}

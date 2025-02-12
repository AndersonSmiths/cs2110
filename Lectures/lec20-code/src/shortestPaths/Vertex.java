package shortestPaths;

/**
 * Represents a vertex in a graph labeled with a string.
 */
public interface Vertex {
    /**
     * Return this vertex's label.
     */
    String label();

    /**
     * Return an object supporting iteration over all of the edges connecting this vertex to another
     * vertex in the graph.  This vertex serves as the "source" vertex for each such edge.
     */
    Iterable<Edge> outgoingEdges();

    /**
     * Return the number of edges connecting this vertex to another vertex in the graph.
     */
    int outDegree();
}

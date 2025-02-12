package traversals;

/**
 * Represents a mutable (appendable) directed graph whose vertices are labeled by strings and whose
 * edges have floating-point weights.  At most one edge (in each direction) may connect any pair of
 * vertices.
 */
public interface Graph {
    /**
     * Return an object supporting iteration over all of the vertices in this graph.  This graph
     * should not be modified while iteration is in progress.
     */
    Iterable<Vertex> vertices();

    /**
     * Return the number of vertices in this graph.
     */
    int vertexCount();

    /**
     * Return the number of edges in this graph.
     */
    int edgeCount();

    /**
     * Return the Vertex in this graph labeled with `label`.  Throw NoSuchElementException` if no
     * vertex with that label is in this graph.
     */
    Vertex getVertex(String label);

    /**
     * Return whether an edge exists from the vertex labeled `srcLabel` to the vertex labeled
     * `dstLabel` in this graph.  Throw NoSuchElementException if vertices with these labels are not
     * in this graph.
     */
    boolean areAdjacent(String srcLabel, String dstLabel);

    /* Mutation methods */

    /**
     * Add a vertex with label `label` to this graph.  Has no effect if a vertex with that label is
     * already in this graph.
     */
    void addVertex(String label);

    /**
     * Add an edge with weight `weight` from the vertex labeled `srcLabel` to the vertex labeled
     * `dstLabel` in this graph.  Throw IllegalArgumentException if an edge already exists between
     * these vertices.  Throw NoSuchElementException if vertices with these labels are not in this
     * graph.
     */
    void connectVertices(String srcLabel, String dstLabel, double weight);
}
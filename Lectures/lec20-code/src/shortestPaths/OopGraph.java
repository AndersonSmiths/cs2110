package shortestPaths;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Implements the Graph interface with outgoing edges stored in Vertex objects.  Vertices are
 * tracked in a map, enabling fast lookup given their index.
 */
public class OopGraph implements Graph {
    /**
     * Map of vertex labels to vertex objects containing all vertices in this graph.
     */
    private Map<String, OopVertex> vertices;

    /**
     * Create a new OopGraph with no vertices.
     */
    public OopGraph() {
        // Using a LinkedHashMap gives us the benefits of a HashMap while making iteration order
        // deterministic.  Vertices will be iterated in the order they were added to this graph.
        vertices = new LinkedHashMap<>();
    }

    @Override
    public Iterable<Vertex> vertices() {
        // Defend against rep exposure by making returned value unmodifiable.
        return Collections.unmodifiableCollection(vertices.values());
    }

    @Override
    public int vertexCount() {
        return vertices.size();
    }

    @Override
    public int edgeCount() {
        // Time complexity: O(V)
        int count = 0;
        for (Vertex v : vertices()) {
            count += v.outDegree();
        }
        return count;
    }

    @Override
    public Vertex getVertex(String label) {
        Vertex ans = vertices.get(label);

        if (ans == null) {
            throw new NoSuchElementException(
                    "Graph does not contain vertex with label " + label);
        }

        return ans;
    }

    @Override
    public boolean areAdjacent(String srcLabel, String dstLabel) {
        OopVertex src = vertices.get(srcLabel);

        if (src == null) {
            throw new NoSuchElementException(
                    "Graph does not contain vertex with label " + srcLabel);
        }
        if (!vertices.containsKey(dstLabel)) {
            throw new NoSuchElementException(
                    "Graph does not contain vertex with label " + srcLabel);
        }

        return src.hasAdjacent(dstLabel);
    }

    /* Mutation methods */

    @Override
    public void addVertex(String label) {
        if (!vertices.containsKey(label)) {
            vertices.put(label, new OopVertex(label));
        }
    }

    @Override
    public void connectVertices(String srcLabel, String dstLabel, double weight) {
        OopVertex src = vertices.get(srcLabel);
        OopVertex dst = vertices.get(dstLabel);

        // Check for non-existing vertices or pre-existing edge.
        if (src == null) {
            throw new NoSuchElementException(
                    "Graph does not contain vertex with label " + srcLabel);
        }
        if (dst == null) {
            throw new NoSuchElementException(
                    "Graph does not contain vertex with label " + srcLabel);
        }
        if (src.hasAdjacent(dstLabel)) {
            throw new IllegalArgumentException(
                    "An edge already connects " + srcLabel + " to " + dstLabel);
        }

        src.addEdge(new Edge(dst, weight));
    }


    /**
     * Implements the Vertex interface by storing a map of adjacent vertex labels associated with
     * Edge objects connecting to the corresponding vertex.
     */
    private static class OopVertex implements Vertex {
        /**
         * This vertex's label.
         */
        private final String label;

        /**
         * Map associating adjacent vertex labels to Edge objects connecting to the corresponding
         * vertex.
         */
        private Map<String, Edge> outgoing;

        /**
         * Create a new Vertex with label `label` and no adjacent vertices.
         */
        public OopVertex(String label) {
            this.label = label;
            // Using a LinkedHashMap gives us the benefits of a HashMap while making iteration order
            // deterministic.  Edges will be iterated in the order they were added to this vertex.
            outgoing = new LinkedHashMap<>();
        }

        @Override
        public String label() {
            return label;
        }

        @Override
        public Iterable<Edge> outgoingEdges() {
            // Defend against rep exposure by making returned value unmodifiable.
            return Collections.unmodifiableCollection(outgoing.values());
        }

        @Override
        public int outDegree() {
            return outgoing.size();
        }

        /**
         * Return whether a vertex with label `dstLabel` is adjacent to this vertex.
         */
        public boolean hasAdjacent(String dstLabel) {
            return outgoing.containsKey(dstLabel);
        }

        /**
         * Add outgoing edge `edge` to this vertex's adjacency map.
         */
        void addEdge(Edge edge) {
            outgoing.put(edge.destination().label(), edge);
        }
    }
    

    /**
     * Create a directed graph based on vertices and edges listed in file `fileName`.
     * The file format is similar to GraphViz dot files.  A line must follow one of these patterns:
     * * Blank line (or only whitespace) - ignored
     * * Comment line starting with '#' - ignored
     * * Isolated vertex line: any label not containing {@code "->"}
     * * Edge line with format {@code srcLabel -> dstLabel}
     * Spaces at the beginning and end of vertex labels are trimmed.
     * All edges are given a weight of 1.
     */
    public static Graph readGraph(String fileName) throws IOException {
        Graph g = new OopGraph();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                // Skip blank lines and comment lines
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Parse vertex labels from line
                String[] tokens = line.split("->");
                if (tokens.length > 2) {
                    throw new IOException("Invalid file format");
                }
                String srcLabel = tokens[0].trim();

                // Ensure first vertex is in graph
                g.addVertex(srcLabel);

                // If line defines an edge, not just a vertex, add the edge
                if (tokens.length == 2) {
                    String dstLabel = tokens[1].trim();
                    // Ensure adjacent vertex is in graph
                    g.addVertex(dstLabel);

                    // Add edge
                    double weight = 1;
                    g.connectVertices(srcLabel, dstLabel, weight);
                }
            }
        }
        return g;
    }

    static Graph readDot(String filename) throws IOException {
        try (FileReader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr)) {
            return readDot(br);
         }
    }

    static Graph readDot(BufferedReader in) throws IOException {
        Graph g = new OopGraph();
        Pattern edgePat = Pattern.compile("\\s*(?:\"([^\"]*)\"|(\\S+))\\s*(-[->])\\s*(?:\"([^\"]*)\"|(\\S+))\\s*(?:\\[([^\\]]*)\\])?\\s*;?\\s*");
        // TODO: non-integer lengths, ignore other attrs
        Pattern attrsPat = Pattern.compile("\\s*len\\s*=\\s*(\\d+)\\s*");
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            Matcher m = edgePat.matcher(line);
            if (m.matches()) {
                String src = m.group(1);
                if (src == null) {
                    src = m.group(2);
                }

                String edgeOp = m.group(3);

                String dst = m.group(4);
                if (dst == null) {
                    dst = m.group(5);
                }

                var weight = 1.0;
                String attrs = m.group(6);
                if (attrs != null) {
                    var am = attrsPat.matcher(attrs);
                    //if (am.matches()) {
                    if (am.find()) {
                        weight = Double.parseDouble(am.group(1));
                    }
                }

                // Ensure vertices are in graph
                g.addVertex(src);
                g.addVertex(dst);

                // Add edge(s)
                g.connectVertices(src, dst, weight);
                if ("--".equals(edgeOp)) {
                    g.connectVertices(dst, src, weight);
                }
            }
        }
        return g;
    }
}

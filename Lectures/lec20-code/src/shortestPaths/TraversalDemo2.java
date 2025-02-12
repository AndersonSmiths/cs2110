package shortestPaths;

public class TraversalDemo2 {
    public static void main(String[] args) {
        // Create graph from lec24 (plus f).
        Graph g = new OopGraph();
        g.addVertex("s");
        g.addVertex("a");
        g.addVertex("b");
        g.addVertex("c");
        g.addVertex("d");
        g.addVertex("f");
        g.connectVertices("s", "a", 1);
        g.connectVertices("s", "b", 1);
        g.connectVertices("a", "b", 1);
        g.connectVertices("a", "c", 1);
        g.connectVertices("b", "c", 1);
        g.connectVertices("b", "d", 1);
        g.connectVertices("c", "d", 1);
        g.connectVertices("d", "f", 1);

        System.out.println("BFS distances from s:");
        System.out.println(Traversals.bfsDistances(g.getVertex("s")));
    }
}

package traversals;

import java.io.IOException;

public class TraversalDemo {
    public static void main(String[] args) throws IOException {
        // Create graph from slide 12.
        Graph g = new OopGraph();
        g.addVertex("1");
        g.addVertex("2");
        g.addVertex("3");
        g.addVertex("4");
        g.addVertex("5");
        g.addVertex("6");
        g.addVertex("7");
        g.addVertex("8");
        g.connectVertices("1", "2", 1);
        g.connectVertices("1", "5", 1);
        g.connectVertices("1", "7", 1);
        g.connectVertices("2", "3", 1);
        g.connectVertices("2", "5", 1);
        g.connectVertices("3", "5", 1);
        g.connectVertices("7", "5", 1);
        g.connectVertices("7", "8", 1);
        g.connectVertices("4", "5", 1);
        g.connectVertices("4", "6", 1);

//        System.out.println("BFS visit order (starting from 1):");
//        Traversals.bfsWalk(g.getVertex("1"), v -> System.out.println(v));
//        System.out.println();
//
//        System.out.println("BFS distances from 1:");
//        System.out.println(Traversals.bfsDistances(g.getVertex("1")));
//        System.out.println();

        System.out.println("DFS visit and settlement order (starting from 1):");
        Indenter indenter = new Indenter();
        Traversals.dfsWalk(g.getVertex("1"), v -> indenter.push("visiting " + v),
                v -> indenter.pop("settling " + v));
        System.out.println();

        System.out.println("Topological ordering:");
        try {
            System.out.println(Traversals.topologicalOrderWalk(g));
            System.out.println(Traversals.topologicalOrderKahns(g));
        } catch (IllegalArgumentException e) {
            System.out.println("Graph is cyclic; no topological ordering exists");
        }

        /* Course prerequisites */

//        Graph courses = OopGraph.readGraph("courses.txt");
//        System.out.println("Topological ordering of CS courses:");
//        try {
//            System.out.println(Traversals.topologicalOrderKahns(courses));
//        } catch (IllegalArgumentException e) {
//            System.out.println("Graph is cyclic; no topological ordering exists");
//        }
    }

    /**
     * Prints strings with an indent corresponding to a "stack depth".  Pushing increases the depth,
     * while popping decreases it.  Indentation is two spaces per depth level.
     */
    static class Indenter {
        /** The current stack depth (amount of indentation). */
        private int depth = 0;

        /** Print `s` with the current level of indentation, then increase the stack depth. */
        public void push(String s) {
            print(s);
            depth += 1;
        }

        /** Decrease the stack depth, then print `s` with the current level of indentation. */
        public void pop(String s) {
            depth -= 1;
            print(s);
        }

        /** Print a line containing `s` to `System.out` with the current level of indentation. */
        public void print(String s) {
            System.out.println("  ".repeat(depth) + s);
        }
    }
}
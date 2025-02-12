package traversals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TraversalTest {

    @DisplayName("GIVEN an empty graph, "
            + "WHEN obtaining the topological order, "
            + "THEN we get an empty list")
    @Test
    void testEmptyGraph() {
        Graph emptyGraph = new OopGraph();
        List<String> topoSortList = Traversals.topologicalOrderWalk(emptyGraph);
        assertTrue(topoSortList.isEmpty());
    }

    @DisplayName("GIVEN a graph with a single vertex, "
            + "WHEN obtaining the topological order, "
            + "THEN we get the singular node in the first index")
    @Test
    void testSingleNodeGraph() {
        Graph singleNodeGraph = new OopGraph();
        singleNodeGraph.addVertex("a");
        List<String> topoSortList = Traversals.topologicalOrderWalk(singleNodeGraph);
        assertEquals("a", topoSortList.get(0));
    }

    @DisplayName("GIVEN a graph with an edge from a to b, "
            + "WHEN obtaining the topological order, "
            + "THEN a should come before b in the topological ordering")
    @Test
    void testSingleEdgeGraph() {
        Graph twoNodeGraph = new OopGraph();
        twoNodeGraph.addVertex("a");
        twoNodeGraph.addVertex("b");
        twoNodeGraph.connectVertices("a", "b", 3);
        List<String> topoSortList = Traversals.topologicalOrderWalk(twoNodeGraph);
        // make sure b is before a
        int aIndex = 0;
        int bIndex = 0;
        for (int i = 0; i < topoSortList.size(); i++) {
            if (topoSortList.get(i).equals("a")) {
                aIndex = i;
            } else if (topoSortList.get(i).equals("b")) {
                bIndex = i;
            }
        }
        assertTrue(aIndex < bIndex);
    }

    @DisplayName("GIVEN a graph with an edge from ")
    @Test
    void testRecursive() {

    }


    // TODO: write more tests for topologicalOrderWalk() and find the bug!

}

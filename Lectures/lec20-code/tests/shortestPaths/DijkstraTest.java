package shortestPaths;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.Test;
import shortestPaths.DijkstraDemo.PathEnd;

public class DijkstraTest {

    // TODO: If you've implemented DijkstraDemo.shortestPaths(),
    //  write some tests for it here!

    @Test
    void testSingleVertexGraph() {
        Graph emptyGraph = new OopGraph();
        emptyGraph.addVertex("a");
        Vertex vertexA = emptyGraph.getVertex("a");
        Map<String, PathEnd> paths = DijkstraDemo.shortestPaths(vertexA);
        assertEquals(1, paths.size());
    }

}

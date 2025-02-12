package diver;

import datastructures.HeapPQueue;
import datastructures.PQueue;
import datastructures.SlowPQueue;
import game.*;
import graph.ShortestPaths;

import java.util.*;


/** This is the place for your implementation of the {@code SewerDiver}.
 */
public class McDiver implements SewerDiver {

    /** See {@code SewerDriver} for specification. */
    @Override
    public void seek(SeekState state) {
        dfsWalk(state);
    }

//    public void djShortest(SeekState state) {
//        PQueue<Long> frontier = new HeapPQueue<>();
//        Map<Long, Integer> distances = new HashMap<>();
//        // bestPaths records best path
//        Map<Long, Long> bestPaths = new HashMap<>();
//        // source distance of 0 from self
//        // add frontier, 0 priority
//        long u = state.currentLocation();
//        distances.put(u, state.distanceToRing());
//        frontier.add(u, 0);
//
//        // frontier contains unsettled locations
//        while (!frontier.isEmpty()) {
//            // if ring is reached then stop
//            if (state.distanceToRing() == 0) {
//                return;
//            }
//
//            long min = frontier.extractMin();
//            int minDist = distances.get(min);
//
//            for (NodeStatus w : state.neighbors()) {
//                long loc = w.getId();
//                // can only travel 1 block at a time
//                int dist = minDist + 1;
//
//                // ensure not null
//                if (!distances.containsKey(loc)) {
//                    bestPaths.put(loc, min);
//                    distances.put(loc, dist);
//                    frontier.add(loc, dist);
//                }
//                if (dist > minDist) {
//                    frontier.changePriority(loc, dist);
//                }
//            };
//
//
//        while (state.distanceToRing() != 0) {
//            if (bestPaths.containsKey(state.currentLocation())) {
//                state.moveTo(bestPaths.get(state.currentLocation()));
//            }
//        }
//
//        }
//
//
//    }
    /**
     * McDiver is standing on a node given by state s, call it u. Every
     * node reachable along paths of unvisited nodes from u are to be visited
     * The location of where McDiver is standing must be stated upon termination.
     * Precondition: u is unvisited.
     */
   public void dfsWalk(SeekState s) {;
       HashSet<Long> visitedNodes = new HashSet<>();
       // node that McDiver is standing on
       long u = s.currentLocation();
       // asserting pre-conditon
       assert !visitedNodes.contains(u);
       visitedNodes.add(u);

       // Stack for dfs iterative solution
       Stack<Long> dfsStack = new Stack<>();
       dfsStack.push(u);

       // inv: McDiver is located at the most recently pushed node
       // precondition: stack is not empty
       while (!dfsStack.isEmpty()) {
           // uncertain if we should pop at this point
           long currNode = dfsStack.peek();

           // if currNode unvisited, add to visitedNodes
           if (!visitedNodes.contains(currNode)) {
               visitedNodes.add(currNode);
           }
           // distance of 0 means that ring has been reached
           if (s.distanceToRing() == 0) {
               return;
           }

           Collection<NodeStatus> neighbors = mySort(s.neighbors());
           //Collection<NodeStatus> sortedNeigbors = mySort(neighbors);

           //boolean unvisitedNeighbors = s.neighbors() != null;
           boolean unvisitedNeighbors = false;
           // enhanced for loop through neighbors
           for (NodeStatus w : neighbors) {
                long id = w.getId();
                if (!visitedNodes.contains(id)) {
                    // pushing location given by id
                    dfsStack.push(id);
                    s.moveTo(id);
                    visitedNodes.add(id);
                    unvisitedNeighbors = true;
                    // depth first so move to neighbor
                    neighbors = mySort(s.neighbors());
                    break;
                }

           }

           // backtrack if no unvisited neighbors
           if (!unvisitedNeighbors) {
               dfsStack.pop();
               s.moveTo(dfsStack.peek());
           }
       }

       // the walker will be standing where they started
       s.moveTo(u);

   }


    /**
     * Helper function that uses insertion sort to return a
     * locations neighbors in order of distance to the ring.
     */
   private Collection<NodeStatus> mySort(Collection<NodeStatus> neighbors) {

       List<NodeStatus> ordered = new ArrayList<>(neighbors);
       // inv: ordered[1:i-1] is sorted version of original ordered array
       for (int i = 1; i < ordered.size(); i++) {
           NodeStatus curr = ordered.get(i);
           int k = i-1;
           while (k >= 0 && ordered.get(k).getDistanceToRing() > curr.getDistanceToRing()) {
               ordered.set(k+1, ordered.get(k));
               k = k-1;
           }
           ordered.set(k+1, curr);
       }
       return ordered;
   }


    /**
     * This method takes in a ScramState and moves McDiver
     * around to collect as much gold as possible before
     * time to escape.
     */
   private void collectGold(ScramState state) {
       Collection<Node> graph = state.allNodes();
       Maze maze = new Maze(new HashSet<>(graph));
       ShortestPaths<Node, Edge> allPaths = new ShortestPaths<>(maze);


       int stepsLeft = state.stepsToGo();
       int stepsTaken = 0;
       allPaths.singleSourceDistances(state.exit());

       // loops until time for McDiver to escape
       while (stepsTaken + allPaths.getDistance(state.currentNode()) < stepsLeft) {
           List<Edge> pathToCoin = null;
           Node destination = null;
           double closest = 999999;
           // loop to find next best node to move to
           for (Node node : graph) {
               allPaths.singleSourceDistances(state.currentNode());
               if (node.getTile().coins() > 0 && allPaths.getDistance(node) < closest && state.currentNode() != node) {
                   closest = allPaths.getDistance(node);
                   destination = node;
                   pathToCoin = allPaths.bestPath(node);
               }
           }

           if (pathToCoin == null) {
               return;
           }
           allPaths.singleSourceDistances(state.exit());
           // loops until time to escape
           while (stepsTaken + allPaths.getDistance(state.currentNode()) < stepsLeft && !(state.currentNode().equals(destination))) {
               for (Edge e : pathToCoin) {
                   state.moveTo(e.destination());
                   // no real logic behind this increment, just works.
                   stepsTaken += 11;
               }
           }
       }

   }

    /**
     * This method utilizes the ShortestPaths and Maze classes
     * to help McDiver navigate to the exit based on Shortest
     * Path/Graph that said classes calculate.
     */
   private void scramHelper(ScramState state) {
       Collection<Node> graph = state.allNodes();

       // name src chosen bc I'm watching a bro code tutorial :)
       Node src = state.currentNode();
       Node exit = state.exit();

       // utilizing the shortest paths function
       Maze maze = new Maze(new HashSet<>(graph));
       ShortestPaths<Node, Edge> allPaths = new ShortestPaths<>(maze);

       // all paths now all the shortest all paths
       allPaths.singleSourceDistances(src);
       List<Edge> shortestExit = allPaths.bestPath(exit);

       // for each loop to move McDiver to exit
       for (Edge e : shortestExit) {
           state.moveTo(e.destination());
       }
   }




    /** See {@code SewerDriver} for specification. */
    @Override
    public void scram(ScramState state) {


        collectGold(state);
        scramHelper(state);
        // TODO 4: Get out of the sewer system before the steps are used up.
        // DO NOT WRITE ALL THE CODE HERE. Instead, write your method elsewhere,
        // with a good specification, and call it from this one.
    }

}

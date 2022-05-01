import java.util.Arrays;
import java.util.Map;

public class BestRouteProblem {

    public BestRoute findBestRoute(int[][] distance, Map<Integer, Integer> constraint) {

        // Sort every row of the matrix by distance in ascending order
        Distance[][] sortedDistance = new Distance[distance.length][distance[0].length];
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                sortedDistance[i][j] = new Distance(j+1, distance[i][j]);
            }
            Arrays.sort(sortedDistance[i]);
        }

        // visited array to remember visited nodes
        boolean[] visited = new boolean[distance.length];

        // path to store nodes while visiting
        int[] path = new int[distance.length];

        // update the best path if current path has minimum cost
        int[] bestPath = new int[distance.length];

        // starting node of the path will always be first node
        path[0] = bestPath[0] = 1;

        // return BestRoute object which contains minimum cost along with best path
        return new BestRoute(findBestRoute(sortedDistance, constraint, visited, 0, 0, Integer.MAX_VALUE, 0, path, bestPath), bestPath);
    }

    public static int findBestRoute(Distance[][] sortedDistance, Map<Integer, Integer> constraint, boolean[] visited, int curPos, int count, int minCost, int cost, int[] path, int[] bestPath) {

        // return minimum cost when all nodes have been visited
        if (count == sortedDistance.length - 1) {

            // replace minCost with cost if cost is lower than minCost
            if (cost < minCost) {
                minCost = cost;

                // store best path with minimum cost
                for (int i = 0; i < path.length; i++) {
                    bestPath[i] = path[i];
                }
            }
            return minCost;
        }

        for (int i = 0; i < sortedDistance.length; i++) {

            // check if node is not visited yet
            if (!visited[sortedDistance[curPos][i].getNode() - 1]) {

                // check if current node has any constraint and constraint node should be visited before
                if (constraint.containsKey(sortedDistance[curPos][i].getNode()) && !visited[constraint.get(sortedDistance[curPos][i].getNode()) - 1]) continue;

                // starting node should not be visited again in between
                if (sortedDistance[curPos][i].getNode() == 1) continue;

                // jump to next node if cost of visiting current node is greater than the minimum cost
                if (cost + sortedDistance[curPos][i].getValue() >= minCost) continue;

                // visit the current node
                visited[sortedDistance[curPos][i].getNode() - 1] = true;

                // add cost of visiting the node
                cost += sortedDistance[curPos][i].getValue();

                // include the node in current path
                path[count + 1] = sortedDistance[curPos][i].getNode();

                // find minimum cost of visiting rest of the node recursively
                minCost = findBestRoute(sortedDistance, constraint, visited, sortedDistance[curPos][i].getNode() - 1, count + 1, minCost, cost, path, bestPath);

                // backtrack and mark the current node as not visited
                visited[sortedDistance[curPos][i].getNode() - 1] = false;

                // remove the cost of visiting current node
                cost -= sortedDistance[curPos][i].getValue();
            }
        }
        // return minimum cost of visiting the nodes
        return minCost;
    }
}

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BestRouteProblemTest {

    @Test
    void findBestRoute() {
        /*
        Note: If lat, long of every node are given instead of cost matrix, then first we need to calculate the cost matrix using haversine formula which is given below:

        public static double haversine(double lat1, double lon1, double lat2, double lon2) {
                // distance between latitudes and longitudes
                double dLat = Math.toRadians(lat2 - lat1);
                double dLon = Math.toRadians(lon2 - lon1);

                // convert to radians
                lat1 = Math.toRadians(lat1);
                lat2 = Math.toRadians(lat2);

                // apply formulae
                double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
                double rad = 6371;
                double c = 2 * Math.asin(Math.sqrt(a));
                return rad * c; // distance in K.M.
        }

        Example: Cost Matrix; Give maximum possible value for paths which are not allowed.
        In the example below:
        "A" should not visit C1, C2 directly, hence distance[0][3] = distance[0][4] = 999;
        "R1", "R2", "C1", "C2" should not visit "A" again, hence distance[1][0] = distance[2][0] = distance[3][0] = distance[4][0] = 999;
        "C1" should not "R1" and "C2" should not visit "R2", hence distance[3][1] = distance[4][2] = 999;

                    40
           /--> R1 ---> C1
        10/     | \50  / |
         /      |  \  /  |
        A     30|   \/   |80
         \      | 60/
        20\     |  /  \  |
           \--> R2 ---> C2
                    70

         */


        int[][] distance = {
                {999, 10, 20, 999, 999},
                {999, 999, 30, 40, 50},
                {999, 30, 999, 60, 70},
                {999, 999, 60, 999, 80},
                {999, 50, 999, 80, 999},
        };

        //

        /*
         * Nodes: {A, R1, R2, C1, C2}
         * Mapping: A => 1; R1 => 2; R2 => 3; C1 => 4; C2 => 5;
         *
         *          A       R1      R2      C1      C2
         *  A       999     10      20      999     999
         *  R1      999     999     30      40      50
         *  R2      999     30      999     60      70
         *  C1      999     999     60      999     80
         *  C2      999     50      999     80      999
         */

        Map<Integer, Integer> constraint = new HashMap<>();

        // C1 (Node 4) should not be visited before R1 (Node 2)
        constraint.put(4, 2);

        // C2 (Node 5) should not be visited before R2 (Node 3)
        constraint.put(5, 3);

        BestRoute bestRoute = new BestRouteProblem().findBestRoute(distance, constraint);

        assertAll(
                () -> assertEquals(170, bestRoute.getCost()),
                () -> assertEquals(Arrays.toString(new int[] {1, 3, 2, 4, 5}), Arrays.toString(bestRoute.getRoute()))
        );

    }
}
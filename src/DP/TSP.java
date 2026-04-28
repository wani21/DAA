package DP;

import java.util.*;

public class TSP {

    static int n = 4; // number of cities
    static int[][] dist = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
    };

    static int VISITED_ALL = (1 << n) - 1;

    static int[][] dp = new int[1 << n][n];

    // TSP function
    public static int tsp(int mask, int pos) {

        // If all cities visited, return cost to go back to start
        if (mask == VISITED_ALL) {
            return dist[pos][0];
        }

        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int ans = Integer.MAX_VALUE;

        for (int city = 0; city < n; city++) {

            if ((mask & (1 << city)) == 0) {

                int newAns = dist[pos][city] +
                        tsp(mask | (1 << city), city);

                ans = Math.min(ans, newAns);
            }
        }

        return dp[mask][pos] = ans;
    }

    public static void main(String[] args) {

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int result = tsp(1, 0); // start from city 0

        System.out.println("Minimum travelling cost: " + result);
    }
}
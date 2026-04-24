package DP;

import java.util.*;

public class Knapsack01 {

    public static int knapsack(int[] weights, int[] values, int n, int W) {

        int[][] dp = new int[n + 1][W + 1];

        // Build DP table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {

                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                }
                else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            values[i - 1] + dp[i - 1][w - weights[i - 1]]
                    );
                }
                else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] weights = new int[n];
        int[] values = new int[n];

        System.out.println("Enter weights:");
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }

        System.out.println("Enter values:");
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }

        System.out.print("Enter capacity of knapsack: ");
        int W = sc.nextInt();

        int maxValue = knapsack(weights, values, n, W);

        System.out.println("Maximum value: " + maxValue);

        sc.close();
    }
}
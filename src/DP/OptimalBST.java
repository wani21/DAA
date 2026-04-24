package DP;

import java.util.*;

public class OptimalBST {

    public static double optimalBST(double[] p, double[] q, int n) {

        double[][] e = new double[n + 2][n + 1];
        double[][] w = new double[n + 2][n + 1];

        // Initialization
        for (int i = 1; i <= n + 1; i++) {
            e[i][i - 1] = q[i - 1];
            w[i][i - 1] = q[i - 1];
        }

        // DP computation
        for (int len = 1; len <= n; len++) {

            for (int i = 1; i <= n - len + 1; i++) {

                int j = i + len - 1;
                e[i][j] = Double.MAX_VALUE;

                w[i][j] = w[i][j - 1] + p[j] + q[j];

                for (int r = i; r <= j; r++) {

                    double cost = e[i][r - 1] + e[r + 1][j] + w[i][j];

                    if (cost < e[i][j]) {
                        e[i][j] = cost;
                    }
                }
            }
        }

        return e[1][n];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of test cases: ");
        int t = sc.nextInt();

        for (int tc = 1; tc <= t; tc++) {

            System.out.println("\nTest Case " + tc + ":");

            System.out.print("Enter number of keys: ");
            int n = sc.nextInt();

            double[] p = new double[n + 1]; // 1-based
            double[] q = new double[n + 1];

            System.out.println("Enter successful search probabilities (p1 to pn):");
            for (int i = 1; i <= n; i++) {
                p[i] = sc.nextDouble();
            }

            System.out.println("Enter unsuccessful search probabilities (q0 to qn):");
            for (int i = 0; i <= n; i++) {
                q[i] = sc.nextDouble();
            }

            double result = optimalBST(p, q, n);

            System.out.println("Minimum Expected Cost: " + result);
        }

        sc.close();
    }
}

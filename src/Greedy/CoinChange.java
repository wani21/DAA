package Greedy;

import java.util.*;

public class CoinChange {

    public static int minCoins(int[] coins, int V) {

        Arrays.sort(coins);

        int count = 0;


        for (int i = coins.length - 1; i >= 0; i--) {

            int used = 0;

            while (V >= coins[i]) {
                V -= coins[i];
                used++;
                count++;
            }

            if (used > 0) {
                System.out.println(coins[i] + " * " + used);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5, 10};
        int V = 28;

        int result = minCoins(coins, V);
        System.out.println("Minimum coins required: " + result);
    }
}

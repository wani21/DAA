package StringMaching;

import java.util.*;

public class BoyerMoore {

    static final int CHAR_SIZE = 256;

    // Function to build Bad Character Table
    public static void badCharHeuristic(String pattern, int[] badChar) {

        for (int i = 0; i < CHAR_SIZE; i++) {
            badChar[i] = -1;
        }

        for (int i = 0; i < pattern.length(); i++) {
            badChar[(int) pattern.charAt(i)] = i;
        }
    }

    // BM Search Function
    public static void search(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        int[] badChar = new int[CHAR_SIZE];

        badCharHeuristic(pattern, badChar);

        int shift = 0;

        boolean found = false;

        long startTime = System.nanoTime();

        while (shift <= (n - m)) {

            int j = m - 1;

            // Compare from right to left
            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) {
                j--;
            }

            // Pattern found
            if (j < 0) {
                System.out.println("Pattern found at index: " + shift);
                found = true;

                shift += (shift + m < n) ? m - badChar[text.charAt(shift + m)] : 1;
            }
            else {
                shift += Math.max(1, j - badChar[text.charAt(shift + j)]);
            }
        }

        long endTime = System.nanoTime();

        if (!found) {
            System.out.println("Pattern not found");
        }

        System.out.println("Time taken (nanoseconds): " + (endTime - startTime));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter pattern: ");
        String pattern = sc.nextLine();

        search(text, pattern);

        sc.close();
    }
}
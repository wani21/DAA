package StringMaching;

import java.util.*;

public class KMP {

    // Function to compute LPS array or pie table
    public static void computeLPS(String pattern, int[] lps) {
        int length = 0; // length of previous longest prefix suffix
        int i = 1;

        lps[0] = 0;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    // KMP search function
    public static void KMPSearch(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        int[] lps = new int[m];

        computeLPS(pattern, lps);

        // Display LPS array
        System.out.println("LPS Array:");
        for (int i = 0; i < m; i++) {
            System.out.print(lps[i] + " ");
        }
        System.out.println();

        int i = 0; // index for text
        int j = 0; // index for pattern

        boolean found = false;

        long startTime = System.nanoTime();

        while (i < n) {

            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                System.out.println("Pattern found at index: " + (i - j));
                found = true;
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {

                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
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

        KMPSearch(text, pattern);

        sc.close();
    }
}

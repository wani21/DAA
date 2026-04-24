package StringMaching;

import java.util.*;

public class RabinKarp {

    static final int d = 256; // number of characters

    public static void search(String text, String pattern, int q) {

        int n = text.length();
        int m = pattern.length();

        int p = 0; // hash value for pattern
        int t = 0; // hash value for text window
        int h = 1;

        // Compute h = pow(d, m-1) % q
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % q;
        }

        // Calculate initial hash values
        for (int i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

        boolean found = false;

        long startTime = System.nanoTime();

        // Slide pattern over text
        for (int i = 0; i <= n - m; i++) {

            // Check hash values
            if (p == t) {

                int j = 0;
                while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
                    j++;
                }

                if (j == m) {
                    System.out.println("Pattern found at index: " + i);
                    found = true;
                }
            }

            // Compute next window hash
            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;

                // Handle negative value
                if (t < 0) {
                    t = t + q;
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

        int q = 101; // a prime number for hashing

        search(text, pattern, q);

        sc.close();
    }
}
package BackTracking;

/**
 * Practical No. 05
 * Design and Implement application based on Backtracking Approach.
 * Implement Naïve Pattern Matching to find the pattern in the given text.
 *
 * Algorithm:
 * - For each position i in the text (0 to n-m):
 *     - Compare pattern characters one by one with text[i], text[i+1], ..., text[i+m-1]
 *     - If ALL m characters match → pattern found at index i
 *     - If ANY character mismatches → backtrack (reset pattern index to 0,
 *       move text index to i+1) and try again
 *
 * Time Complexity : O((n - m + 1) * m)  where n = text length, m = pattern length
 * Space Complexity: O(1)  — no extra data structures used
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NaivePatternMatching {

    /**
     * Searches for all occurrences of 'pattern' inside 'text'
     * using the Naïve (backtracking) approach.
     *
     * @param text    The string to search in
     * @param pattern The string to search for
     * @return A list of starting indices where the pattern is found
     */
    public static List<Integer> naiveSearch(String text, String pattern) {
        List<Integer> matchPositions = new ArrayList<>();

        int n = text.length();    // length of text
        int m = pattern.length(); // length of pattern

        // Edge case: pattern cannot be longer than text
        if (m > n) {
            System.out.println("Pattern is longer than text. No match possible.");
            return matchPositions;
        }

        // Outer loop: slide the window over the text
        // i goes from 0 to (n - m), the last valid starting position
        for (int i = 0; i <= n - m; i++) {

            int j = 0; // Pattern index — starts fresh at each text position (backtracking)

            // Inner loop: compare pattern[j] with text[i + j]
            while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
                j++; // Characters match, move to next character in pattern
            }

            // If j reached m, all pattern characters matched
            if (j == m) {
                matchPositions.add(i); // Record starting index of the match
                System.out.println("  Pattern found at index: " + i
                        + "  →  text[" + i + ".." + (i + m - 1) + "]");
            }
            // If j < m, mismatch occurred — outer loop increments i (backtracking)
        }

        return matchPositions;
    }

    /**
     * Prints a step-by-step trace of the matching process for learning purposes.
     * Shows exactly what happens at each comparison, including backtracks.
     */
    public static void naiveSearchWithTrace(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int totalComparisons = 0;
        int totalBacktracks  = 0;

        System.out.println("\n--- Step-by-step Trace ---");
        System.out.println("Text   : " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println();

        for (int i = 0; i <= n - m; i++) {
            System.out.print("i=" + i + " | Trying: text[" + i + "] to text[" + (i + m - 1) + "] → ");

            int j = 0;
            boolean mismatch = false;

            while (j < m) {
                totalComparisons++;
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    System.out.println("MISMATCH at j=" + j
                            + " (text='" + text.charAt(i + j)
                            + "' vs pattern='" + pattern.charAt(j) + "')  → BACKTRACK");
                    totalBacktracks++;
                    mismatch = true;
                    break;
                }
                j++;
            }

            if (!mismatch) {
                System.out.println("MATCH  ✓  at index " + i);
            }
        }

        System.out.println("\n--- Summary ---");
        System.out.println("Total comparisons : " + totalComparisons);
        System.out.println("Total backtracks  : " + totalBacktracks);
    }

    // -----------------------------------------------------------------------
    // Main Method — Demonstrates the algorithm with examples + user input
    // -----------------------------------------------------------------------
    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("  Practical 05 — Naïve Pattern Matching");
        System.out.println("========================================\n");

        // --- Example 1: Basic demonstration ---
        String text1    = "AABAACAADAABAABA";
        String pattern1 = "AABA";
        System.out.println("Example 1:");
        System.out.println("Text   : " + text1);
        System.out.println("Pattern: " + pattern1);
        List<Integer> result1 = naiveSearch(text1, pattern1);
        System.out.println("All match positions: " + result1 + "\n");

        // --- Example 2: Pattern not found ---
        String text2    = "ABCDEFGH";
        String pattern2 = "XYZ";
        System.out.println("Example 2:");
        System.out.println("Text   : " + text2);
        System.out.println("Pattern: " + pattern2);
        List<Integer> result2 = naiveSearch(text2, pattern2);
        if (result2.isEmpty()) {
            System.out.println("Pattern not found in text.\n");
        }

        // --- Example 3: Pattern found at start and end ---
        String text3    = "ABCABC";
        String pattern3 = "ABC";
        System.out.println("Example 3:");
        System.out.println("Text   : " + text3);
        System.out.println("Pattern: " + pattern3);
        List<Integer> result3 = naiveSearch(text3, pattern3);
        System.out.println("All match positions: " + result3 + "\n");

        // --- Trace mode for a small example to show backtracking ---
        naiveSearchWithTrace("AABCAAB", "AAB");

        // --- Interactive mode: user-provided input ---
        System.out.println("\n--- Enter your own text and pattern ---");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text   : ");
        String userText = sc.nextLine();
        System.out.print("Enter pattern: ");
        String userPattern = sc.nextLine();

        System.out.println("\nSearching...");
        List<Integer> userResult = naiveSearch(userText, userPattern);
        if (userResult.isEmpty()) {
            System.out.println("Pattern \"" + userPattern + "\" not found in the text.");
        } else {
            System.out.println("Pattern found " + userResult.size()
                    + " time(s) at indices: " + userResult);
        }

        sc.close();
    }
}
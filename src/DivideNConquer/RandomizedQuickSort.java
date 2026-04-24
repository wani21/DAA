package DivideNConquer;

import java.util.Random;
import java.util.Scanner;

public class RandomizedQuickSort {

    static int comparisons = 0;
    static Random rand = new Random();

    // Swap function
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Partition with random pivot
    public static int partition(int[] arr, int low, int high) {

        // Random pivot index
        int pivotIndex = low + rand.nextInt(high - low + 1);

        // Move pivot to end
        swap(arr, pivotIndex, high);

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparisons++;

            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // QuickSort function
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Print array
    public static void printArray(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        long startTime = System.nanoTime();

        quickSort(arr, 0, n - 1);

        long endTime = System.nanoTime();

        System.out.println("Sorted Array:");
        printArray(arr);

        System.out.println("Comparisons: " + comparisons);
        System.out.println("Time taken (nanoseconds): " + (endTime - startTime));

        sc.close();
    }
}
package DivideNConquer;

import java.util.Random;

public class QuickSortComparison {

    static int normalComparisons = 0;
    static int randomComparisons = 0;

    static Random rand = new Random();

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    public static int[] generateArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }

    // ---------------- NORMAL QUICKSORT ----------------
    public static int partitionNormal(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            normalComparisons++;

            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void normalQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionNormal(arr, low, high);

            normalQuickSort(arr, low, pi - 1);
            normalQuickSort(arr, pi + 1, high);
        }
    }

    // ---------------- RANDOMIZED QUICKSORT ----------------
    public static int partitionRandom(int[] arr, int low, int high) {

        int pivotIndex = low + rand.nextInt(high - low + 1);
        swap(arr, pivotIndex, high);

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            randomComparisons++;

            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionRandom(arr, low, high);

            randomizedQuickSort(arr, low, pi - 1);
            randomizedQuickSort(arr, pi + 1, high);
        }
    }

    // ---------------- MAIN FUNCTION ----------------
    public static void main(String[] args) {

        int[] sizes = {100, 500, 1000, 2000, 5000};

        for (int size : sizes) {

            int[] arr = generateArray(size);

            int[] arrNormal = copyArray(arr);
            int[] arrRandom = copyArray(arr);

            // Normal QuickSort
            normalComparisons = 0;
            normalQuickSort(arrNormal, 0, arrNormal.length - 1);

            // Randomized QuickSort
            randomComparisons = 0;
            randomizedQuickSort(arrRandom, 0, arrRandom.length - 1);

            System.out.println("Size: " + size);
            System.out.println("Normal Comparisons: " + normalComparisons);
            System.out.println("Randomized Comparisons: " + randomComparisons);
            System.out.println("----------------------------------");
        }
    }
}
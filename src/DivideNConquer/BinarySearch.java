package DivideNConquer;

import java.util.Random;
class Random_binary_search {
    static int randomBinarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        Random rand = new Random();
        while (low <= high) {
            int mid = low + rand.nextInt(high - low + 1);
            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    static double averageTime(int[] arr, int key, int runs) {
        long totalTime = 0;
        for (int i = 0; i < runs; i++) {
            long start = System.nanoTime();
            randomBinarySearch(arr, key);
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return (totalTime / runs) / 1_000_000.0; // ms
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 50000, 100000};
        int runs = 50;
        for (int size : sizes) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = i + 1;
            }
            int key = arr[size - 1]; // worst-case key
            double timeTaken = averageTime(arr, key, runs);
            System.out.println(timeTaken);
        }
    }
}
package DivideNConquer;

public class Linear_search {
    static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
    static int binarySearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == key)
                return mid;
            else if (arr[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        // Different input sizes
        int[] sizes = {1000, 5000, 10000, 50000, 100000};
        // Time arrays (milliseconds)
        double[] linearTimes = new double[sizes.length];
        double[] binaryTimes = new double[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            int n = sizes[i];
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = j + 1;
            }
            int key = n - 1; // near worst case
            // Linear Search timing
            long start = System.nanoTime();
            linearSearch(arr, key);
            long end = System.nanoTime();
            linearTimes[i] = (end - start) / 1_000_000.0;
            // Binary Search timing
            long start1 = System.nanoTime();
            binarySearch(arr, key);
            long end1 = System.nanoTime();
            binaryTimes[i] = (end1 - start1) / 1_000_000.0;
        }
        // Printing results
        System.out.println("Input Size\tLinear Time(ms)\tBinary Time(ms)");
        for (int i = 0; i < sizes.length; i++) {
            System.out.println(
                    sizes[i] + "\t\t" +
                            linearTimes[i] + "\t\t" +
                            binaryTimes[i]
            );
        }
    }
}

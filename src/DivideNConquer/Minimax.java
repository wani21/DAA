package DivideNConquer;

class Minimax {
    static void findMinMax(int[] arr, String caseType) {
        int n = arr.length;
        int min, max;
        int i;
        if (n % 2 == 0) {
            if (arr[0] < arr[1]) {
                min = arr[0];
                max = arr[1];
            } else {
                min = arr[1];
                max = arr[0];
            }
            i = 2;
        } else {
            min = max = arr[0];
            i = 1;
        }
        while (i < n - 1) {
            int localMin, localMax;
            if (arr[i] < arr[i + 1]) {
                localMin = arr[i];
                localMax = arr[i + 1];
            } else {
                localMin = arr[i + 1];
                localMax = arr[i];
            }
            if (localMin < min) {
                min = localMin;
            }
            if (localMax > max) {
                max = localMax;
            }
            i += 2;
        }
        System.out.println("Case Type : " + caseType);
        System.out.println("Minimum : " + min);
        System.out.println("Maximum : " + max);
    }
    public static void main(String[] args) {
        int[] arr1 = {5, 3, 9, 1, 6, 8, 2};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr3 = {9, 8, 7, 6, 5, 4, 3};
        findMinMax(arr1, "Mixed Array");
        findMinMax(arr2, "Sorted Ascending Array");
        findMinMax(arr3, "Sorted Descending Array");
    }
}
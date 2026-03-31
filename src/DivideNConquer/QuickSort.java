package DivideNConquer;

public class QuickSort {

    public static void quicksort(int[]arr,int left,int right) {

        if (left < right) {

            int pivotIndex = partition(arr, left, right);

            //sort left part

            quicksort(arr, left, pivotIndex - 1);

            //sort right part

            quicksort(arr, pivotIndex + 1, right);
        }
    }

    public static int partition(int[]arr,int left , int right){

        int pivot = arr[right];
        int i = left -1;

        for (int j = left; j < right; j++) {

            if (arr[j] < pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // place pivot at correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        return i + 1;
    }

    static void main() {
        int [] arr = {10, 7, 8, 9, 1, 5};

        quicksort(arr,0,arr.length-1);

        //printing the sorted array

        for(int num:arr){
            System.out.println(num+" ");
        }
    }

}




package project20280.sorting;

public class QuickSort {
    public static void quickSort(int[] arr) {

        // no need to do anything if an array is empty or only has 1 element
        if (arr == null || arr.length <= 1) {
            return;
        }
        // recursive call on a whole array
        quickSort(arr, 0, arr.length - 1);
    }

    // recursive helper method
    private static void quickSort(int[] arr, int low, int high) {

        // continue if there are at least 2 elements to sort
        if (low < high) {

            // get the pivot index
            int pivotIndex = partition(arr, low, high);

            // recursive call sectioning the array from left of pivot
            quickSort(arr, low, pivotIndex - 1);

            // another recursive call sectioning the array from the right of the pivot
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {

        // get the pivot from the median function
        int pivot = medianOfThree(arr, low, high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // helper method to move elements around
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // pivot chooser
    public static int medianOfThree(int[] arr, int low, int high) {
        // find the middle index of the array
        int mid = low + (high - low) / 2;

        // sort the 3 values low, mid and high from smallest to largest
        if(arr[low] > arr[mid]) {swap(arr, low, mid);}
        if(arr[low] > arr[high]) {swap(arr, low, high);}
        if(arr[mid] > arr[high]) {swap(arr, mid, high);}

        // we switch the median pivot to the end of an array as our partition method requires the pivot there
        swap(arr, mid, high);
        return arr[high];
    }
}
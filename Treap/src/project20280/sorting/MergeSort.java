package project20280.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeSort {
    //Method to sort a basic array
    public static void sort(int[] arr) {
        Integer[] boxed = new Integer[arr.length];//Holds number objects
        for (int i = 0; i < arr.length; i++) boxed[i] = arr[i];//Copy the plain numbers to the new object array

        mergeSort(boxed, Comparator.naturalOrder());

        for (int i = 0; i < arr.length; i++) arr[i] = boxed[i];
    }
    
    //To sort arrays of any type of object
    public static <T extends Comparable<? super T>> void mergeSort(final T[] data,
                                                                   final Comparator<T> comparator) {
        if (data == null || data.length <= 1) {
            return;
        }

        T[] temp = Arrays.copyOf(data, data.length);//Make a blank backup copy of array
        mergeSortArray(data, temp, 0, data.length - 1, comparator);//Start from the 1st to very last item
    }
    //Recursive function cuts the array in half
    private static <T extends Comparable<? super T>> void mergeSortArray(final T[] data,
                                                                         final T[] temp,
                                                                         final int left,
                                                                         final int right,
                                                                         final Comparator<T> comparator) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;//Find the middle position of the chunk

        mergeSortArray(data, temp, left, mid, comparator);//Sort fron the start to middle
        mergeSortArray(data, temp, mid + 1, right, comparator);//From just after middle to end
        mergeArray(data, temp, left, mid, right, comparator);//Stitch 2 halves together in the right order
    }
    //Zip 2 sorted halves back together
    private static <T extends Comparable<? super T>> void mergeArray(final T[] data,
                                                                     final T[] temp,
                                                                     final int left,
                                                                     final int mid,
                                                                     final int right,
        //Set pointers                                                             final Comparator<T> comparator) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) { 
            if (comparator.compare(data[i], data[j]) <= 0) { //If the item on the left is smaller or equal
                temp[k] = data[i];//Put the left item into the organized backup array
                i++;
            } else { //the item on the right must be smaller
                temp[k] = data[j];
                j++;
            }
            k++;
        }
        //If the right half ran out first
        while (i <= mid) {
            temp[k] = data[i];//Copy the remaining left item
            i++;//Move left pointer
            k++;//move backup pointer
        }
        //If the left half ran out first
        while (j <= right) { 
            temp[k] = data[j];
            j++;//Move right pointer
            k++;
        }
        //Copy the sorted section from the backup
        for (int x = left; x <= right; x++) {
            data[x] = temp[x];//back into the main array
        }
    }
    //Sort lists
    public static <T extends Comparable<? super T>> void mergeSort(final List<T> data,
                                                                   final Comparator<T> comparator) {
        if (data == null || data.size() <= 1) { //If the list is empty or only 1 item
            return;
        }

        int mid = data.size() / 2;//Middle position

        List<T> left = new ArrayList<>(data.subList(0, mid));//New list holding the left half
        List<T> right = new ArrayList<>(data.subList(mid, data.size()));//Right half

        mergeSort(left, comparator);
        mergeSort(right, comparator);

        mergeList(data, left, right, comparator);//Make 2 sorted halvs into main list
    }
    //make 2 sorted list back together
    private static <T extends Comparable<? super T>> void mergeList(final List<T> data,
                                                                    final List<T> left,
                                                                    final List<T> right,
        //Set pointers                                                            final Comparator<T> comparator) {
        int i = 0; //Looking through left list
        int j = 0; //Looking through right list 
        int k = 0; //put the item in the main list

        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) { // If the left item is smaller or equal
                data.set(k, left.get(i));//Overwrite main list with right item
                i++;
            } else {
                data.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < left.size()) {  // If the right list ran out first
            data.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < right.size()) { // If the left list ran out first
            data.set(k, right.get(j));
            j++;
            k++;
        }
    }
}

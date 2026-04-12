package project20280.tree;

import project20280.interfaces.Entry;
import java.util.ArrayList;

public class TreapSort {

    // sort array using Treap
    public static void sort(int[] arr) {
        // empty treap to store the number and the frequency
        TreapMap<Integer, Integer> treap = new TreapMap<Integer, Integer>();

        // go thru the whole array
        int i = 0;
        while (i < arr.length) {
            // check if number is there in the tree and see its frequency
            Integer count = treap.get(arr[i]);

            // if number is not in treap frequency change to 1
            if (count == null) {
                treap.put(arr[i], 1);
            } else {
                // if number is already there increase the frequency by 1
                treap.put(arr[i], count + 1);
            }
            // move to next element in array
            i++;
        }

        // get the treap in sorted order
        ArrayList<Entry<Integer, Integer>> list = treap.inOrderList();

        int index = 0;
        int j = 0;

        // go thru the treap
        while (j < list.size()) {
            // get each number and its frequency
            Entry<Integer, Integer> e = list.get(j);
            int number = e.getKey();
            int count = e.getValue();

            // writes the current number into the array as many times as its frequency
            int k = 0;
            while (k < count) {
                arr[index] = number;
                index++;
                k++;
            }

            // move to next entry of array
            j++;
        }
    }
}
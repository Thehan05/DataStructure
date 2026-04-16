package project20280.sorting;

import java.util.PriorityQueue;

public class PQSort {

    public static void sort(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //Loop through numbers in unsorted array one by one
        for (int val : arr) { //Put the number into the priority queue
            pq.add(val);
        }
        //Loop through the slots of the original array from start to finish
        for (int i = 0; i < arr.length; i++) { //Put the smallest number out if the queue
            arr[i] = pq.poll();
        }
    }
}

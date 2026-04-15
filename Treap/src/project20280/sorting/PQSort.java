package project20280.sorting;

import java.util.PriorityQueue;

public class PQSort {

    public static void sort(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int val : arr) {
            pq.add(val);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = pq.poll();
        }
    }
}
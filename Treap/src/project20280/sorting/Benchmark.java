package project20280.sorting;
import project20280.tree.TreapSort;

import java.util.*;
public class Benchmark {

    public static int[] generateData(int n, String pattern) { //Create an array to sort
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;//Fill the array of numbers in order
        }

        Random rand = new Random(42);//Random number generator
        //Check the data that want to be tested
        switch (pattern) {
            case "random":
                for (int i = n - 1; i > 0; i--) { 
                    int j = rand.nextInt(i + 1);
                    int tmp = data[i];// Hold the current number temporarily
                    data[i] = data[j];//Swap with random position
                    data[j] = tmp;
                }
                break;

            case "nearly_sorted"://If the numbers are mostly in order
                int swaps = Math.max(1, n / 10);//Calculate how many swaps
                for (int i = 0; i < swaps; i++) {
                    int a = rand.nextInt(n);//Random position
                    int b = rand.nextInt(n);
                    int tmp = data[a];
                    data[a] = data[b];//put the number from b into a
                    data[b] = tmp;
                }
                break;

            case "reverse"://If the numbers backwards
                for (int i = 0; i < n / 2; i++) { //Halfway through the list
                    int tmp = data[i];
                    data[i] = data[n - 1 - i];//Move the number from the back to the front
                    data[n - 1 - i] = tmp;
                }
                break;
        }

        return data;
    }
    //Helper function to make a copy of an array
    private static int[] copy(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }
    
    //Test how fast
    public static long benchmarkTreapSort(int[] data, int iteration) {
        for(int i = 0; i < 3; i++) { //Run 3 times
            int[] arr = copy(data);
            TreapSort.sort(arr);
        }

        long total = 0;
        for(int i = 0; i < iteration; i++) {
            int[] arr = copy(data);  //get unsorted copy of the data
            long start = System.nanoTime();
            TreapSort.sort(arr);
            long end = System.nanoTime();
            total += (end - start);  //Add tge time it took to the total time
        }
        return total / iteration;   //Average time 
    }

    public static long benchmarkPQSort(int[] data, int iteration) {
        for (int i = 0; i < 3; i++) { //3 times
            int[] arr = copy(data);
            PQSort.sort(arr); //Sort it
        }

        long total = 0;
        for (int i = 0; i < iteration; i++) { 
            int[] arr = copy(data);
            long start = System.nanoTime(); //Start timer
            PQSort.sort(arr);
            long end = System.nanoTime();
            total += (end - start);
        }

        return total / iteration;
    }


    public static long benchmarkCollectionsSort(int[] data, int iterations) {
        for (int i = 0; i < 3; i++) { //3 times warm up
            List<Integer> list = new ArrayList<>(); // Needs a list
            for (int x : data) list.add(x); //out all array numbers in to the list
            Collections.sort(list);
        }

        long total = 0;
        for (int i = 0; i < iterations; i++) { //Loop for test
            List<Integer> list = new ArrayList<>(); //Making a new list
            for (int x : data) list.add(x);//Fill it with unsorted numbers

            long start = System.nanoTime();
            Collections.sort(list); //java sort
            long end = System.nanoTime();
            total += (end - start);
        }
        return total / iterations;
    }
    //Test how fast the quicksort is
    public static long benchmarkQuickSort(int[] data, int iterations) {
        for (int i = 0; i < 3; i++) { //3 times
            int[] arr = copy(data);//Copy the data
            QuickSort.quickSort(arr);
        }

        long total = 0;
        for (int i = 0; i < iterations; i++) { //Loop for test
            int[] arr = copy(data);//Get data
            long start = System.nanoTime();//Start timer
            QuickSort.quickSort(arr);
            long end = System.nanoTime();
            total += (end - start);//Add to local time
        }
        return total / iterations;
    }
    //Test how fast the mergesort is
    public static long benchmarkMergeSort(int[] data, int iterations) {  
        for (int i = 0; i < 3; i++) { // 3 times warmup
            int[] arr = copy(data);//copy the data
            MergeSort.sort(arr);
        }

        long total = 0;
        for (int i = 0; i < iterations; i++) { // loop for the test
            int[] arr = copy(data);//Get data
            long start = System.nanoTime();//Start timer
            MergeSort.sort(arr);
            long end = System.nanoTime();
            total += (end - start);
        }
        return total / iterations;//Give back the average time
    }

}

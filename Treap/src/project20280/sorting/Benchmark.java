package project20280.sorting;
import project20280.tree.TreapSort;

import java.util.*;
public class Benchmark {

    public static int[] generateData(int n, String pattern) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;
        }

        Random rand = new Random(42);

        switch (pattern) {
            case "random":
                for (int i = n - 1; i > 0; i--) {
                    int j = rand.nextInt(i + 1);
                    int tmp = data[i];
                    data[i] = data[j];
                    data[j] = tmp;
                }
                break;

            case "nearly_sorted":
                int swaps = Math.max(1, n / 10);
                for (int i = 0; i < swaps; i++) {
                    int a = rand.nextInt(n);
                    int b = rand.nextInt(n);
                    int tmp = data[a];
                    data[a] = data[b];
                    data[b] = tmp;
                }
                break;

            case "reverse":
                for (int i = 0; i < n / 2; i++) {
                    int tmp = data[i];
                    data[i] = data[n - 1 - i];
                    data[n - 1 - i] = tmp;
                }
                break;
        }

        return data;
    }

    private static int[] copy(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    public static long benchmarkTreapSort(int[] data, int iteration) {
        for(int i = 0; i < 3; i++) {
            int[] arr = copy(data);
            TreapSort.sort(arr);
        }

        long total = 0;
        for(int i = 0; i < iteration; i++) {
            int[] arr = copy(data);
            long start = System.nanoTime();
            TreapSort.sort(arr);
            long end = System.nanoTime();
            total += (end - start);
        }
        return total / iteration;
    }

    public static long benchmarkPQSort(int[] data, int iteration) {
        for (int i = 0; i < 3; i++) {
            int[] arr = copy(data);
            PQSort.sort(arr);
        }

        long total = 0;
        for (int i = 0; i < iteration; i++) {
            int[] arr = copy(data);
            long start = System.nanoTime();
            PQSort.sort(arr);
            long end = System.nanoTime();
            total += (end - start);
        }

        return total / iteration;
    }


    public static long benchmarkCollectionsSort(int[] data, int iterations) {
        for (int i = 0; i < 3; i++) {
            List<Integer> list = new ArrayList<>();
            for (int x : data) list.add(x);
            Collections.sort(list);
        }

        long total = 0;
        for (int i = 0; i < iterations; i++) {
            List<Integer> list = new ArrayList<>();
            for (int x : data) list.add(x);

            long start = System.nanoTime();
            Collections.sort(list);
            long end = System.nanoTime();
            total += (end - start);
        }
        return total / iterations;
    }

    public static long benchmarkQuickSort(int[] data, int iterations) {
        for (int i = 0; i < 3; i++) {
            int[] arr = copy(data);
            QuickSort.quickSort(arr);
        }

        long total = 0;
        for (int i = 0; i < iterations; i++) {
            int[] arr = copy(data);
            long start = System.nanoTime();
            QuickSort.quickSort(arr);
            long end = System.nanoTime();
            total += (end - start);
        }
        return total / iterations;
    }

    public static long benchmarkMergeSort(int[] data, int iterations) {
        for (int i = 0; i < 3; i++) {
            int[] arr = copy(data);
            MergeSort.sort(arr);
        }

        long total = 0;
        for (int i = 0; i < iterations; i++) {
            int[] arr = copy(data);
            long start = System.nanoTime();
            MergeSort.sort(arr);
            long end = System.nanoTime();
            total += (end - start);
        }
        return total / iterations;
    }


    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 2000, 5000, 10000};
        int iterations = 5;
        String[] patterns = {"random", "nearly_sorted", "reverse"};

        for (String pattern : patterns) {
            System.out.println("\nPattern: " + pattern);
            System.out.println("======================================================================================");
            System.out.printf("| %-6s | %-13s | %-13s | %-13s | %-13s | %-10s|%n",
                    "n", "TreapSort", "PQSort", "TimSort", "QuickSort", "MergeSort");
            System.out.println("======================================================================================");

            for (int n : sizes) {
                int[] data = generateData(n, pattern);

                long treap = benchmarkTreapSort(data, iterations);
                long pq    = benchmarkPQSort(data, iterations);
                long tim   = benchmarkCollectionsSort(data, iterations);
                long quick = benchmarkQuickSort(data, iterations);
                long merge = benchmarkMergeSort(data, iterations);

                System.out.printf("| %-6d | %-13.3f | %-13.3f | %-13.3f | %-13.3f | %-10f|%n",
                        n,
                        treap  / 1_000_000.0,
                        pq     / 1_000_000.0,
                        tim    / 1_000_000.0,
                        quick  / 1_000_000.0,
                        merge  / 1_000_000.0);
            }

            System.out.println("======================================================================================");
        }
    }
}

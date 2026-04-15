package project20280.sorting;

import org.junit.jupiter.api.Test;

public class sortingTest {
    private static final int[] SIZES = {100, 500, 1000, 2000, 5000, 10000};
    private static final String[] PATTERNS = {"random", "nearly_sorted", "reverse"};
    private static final int ITERATIONS = 5;

    private void printHeader(String pattern) {
        System.out.println("\n Pattern: " + pattern);
        System.out.println("+--------+---------------+---------------+---------------+---------------+---------------+");
        System.out.println("|   n    |  TreapSort(ms)|   PQSort(ms)  |  TimSort(ms)  | QuickSort(ms) | MergeSort(ms) |");
        System.out.println("+--------+---------------+---------------+---------------+---------------+---------------+");
    }

    private void printFooter() {
        System.out.println("+--------+---------------+---------------+---------------+---------------+---------------+");
    }
    @Test
    void testRandom() {
        printHeader("random");
        for (int n : SIZES) {
            int[] data = Benchmark.generateData(n, "random");
            print(n, data);
        }
        printFooter();
    }

    @Test
    void testNearlySorted() {
        printHeader("nearly_sorted");
        for (int n : SIZES) {
            int[] data = Benchmark.generateData(n, "nearly_sorted");
            print(n, data);
        }
        printFooter();
    }

    @Test
    void testReverse() {
        printHeader("reverse");
        for (int n : SIZES) {
            int[] data = Benchmark.generateData(n, "reverse");
            print(n, data);
        }
        printFooter();
    }

    private void print(int n, int[] data) {
        long treap = Benchmark.benchmarkTreapSort(data, ITERATIONS);
        long pq    = Benchmark.benchmarkPQSort(data, ITERATIONS);
        long tim   = Benchmark.benchmarkCollectionsSort(data, ITERATIONS);
        long quick = Benchmark.benchmarkQuickSort(data, ITERATIONS);
        long merge = Benchmark.benchmarkMergeSort(data, ITERATIONS);

        System.out.printf("| %-6d | %-13.4f | %-13.4f | %-13.4f | %-13.4f | %-13.4f |%n",
                n,
                treap / 1_000_000.0,
                pq    / 1_000_000.0,
                tim   / 1_000_000.0,
                quick / 1_000_000.0,
                merge / 1_000_000.0);
    }
}

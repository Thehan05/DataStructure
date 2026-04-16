package project20280.sorting;

import org.junit.jupiter.api.Test;

public class sortingTest {
    private static final int[] SIZES = {100, 500, 1000, 2000, 5000, 10000};//Sets the list sizes
    private static final String[] PATTERNS = {"random", "nearly_sorted", "reverse"};
    private static final int ITERATIONS = 5;// times to run each test

    private void printHeader(String pattern) {
        System.out.println("\n Pattern: " + pattern);
        System.out.println("+--------+---------------+---------------+---------------+---------------+---------------+");
        System.out.println("|   n    |  TreapSort(ms)|   PQSort(ms)  |  TimSort(ms)  | QuickSort(ms) | MergeSort(ms) |");
        System.out.println("+--------+---------------+---------------+---------------+---------------+---------------+");
    }

    private void printFooter() { //Draw the bottom line
        System.out.println("+--------+---------------+---------------+---------------+---------------+---------------+");
    }
    @Test//Test for random numbers
    void testRandom() {
        printHeader("random");//Draws the table header
        for (int n : SIZES) { //Loop through every size 
            int[] data = Benchmark.generateData(n, "random");//Benchmark to build a random array of size n
            print(n, data);
        }
        printFooter();
    }

    @Test //test for the numbers that are already mostly in order
    void testNearlySorted() {
        printHeader("nearly_sorted");
        for (int n : SIZES) {// Loops through every size again
            int[] data = Benchmark.generateData(n, "nearly_sorted");
            print(n, data);
        }
        printFooter();
    }

    @Test// Test for numbers that are completely backwards
    void testReverse() {
        printHeader("reverse");
        for (int n : SIZES) {
            int[] data = Benchmark.generateData(n, "reverse");//builds the array of backwards numbers
            print(n, data);
        }
        printFooter();//Close line
    }

    private void print(int n, int[] data) {
        long treap = Benchmark.benchmarkTreapSort(data, ITERATIONS);//Average time it took to TreapSort run
        long pq    = Benchmark.benchmarkPQSort(data, ITERATIONS);//Average time PQsort
        long tim   = Benchmark.benchmarkCollectionsSort(data, ITERATIONS);//Average time Timsort
        long quick = Benchmark.benchmarkQuickSort(data, ITERATIONS);//Average time quickjsort
        long merge = Benchmark.benchmarkMergeSort(data, ITERATIONS); //Average mergesort

        System.out.printf("| %-6d | %-13.4f | %-13.4f | %-13.4f | %-13.4f | %-13.4f |%n",
                n,
                treap / 1_000_000.0, //Nanoseconds into milliseconds
                pq    / 1_000_000.0,
                tim   / 1_000_000.0,
                quick / 1_000_000.0,
                merge / 1_000_000.0);
    }
}

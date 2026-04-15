package project20280.tree;

import org.junit.jupiter.api.Test;
import java.util.List;

public class PerformanceComparisonTest {

    private static final int[] SIZES = {100, 500, 1000, 2000, 5000, 10000};
    private static final String[] PATTERNS = {"random", "ascending", "descending", "partial"};
    private static final int ITERATIONS = 5;

    private void printHeader(String operation, String pattern) {
        System.out.println("\n Operation: " + operation + " | Pattern: " + pattern);
        System.out.println("+--------+------------------+------------------+------------------+");
        System.out.println("|   n    |      Treap (ms)  |     AVL (ms)     |  Java TM (ms)    |");
        System.out.println("+--------+------------------+------------------+------------------+");
    }

    private void printFooter() {
        System.out.println("+--------+------------------+------------------+------------------+");
    }


    @Test
    void testBatchInsert() {
        System.out.println("\n=== Batch Insert ===");
        for (String pattern : PATTERNS) {
            printHeader("Batch Insert", pattern);
            for (int n : SIZES) {
                List<Integer> data = PerformanceComparison.generateData(n, pattern);

                long treap = PerformanceComparison.benchmarkTreapBatchInsert(data, ITERATIONS);
                long avl   = PerformanceComparison.benchmarkAVLBatchInsert(data, ITERATIONS);
                long java  = PerformanceComparison.benchmarkJavaTreeMapBatchInsert(data, ITERATIONS);

                System.out.printf("| %-6d | %-16.4f | %-16.4f | %-16.4f |%n",
                        n, treap/1_000_000.0, avl/1_000_000.0, java/1_000_000.0);
            }
            printFooter();
        }
    }

    @Test
    void testSuccessfulSearch() {
        System.out.println("\n=== Successful Search ===");
        for (String pattern : PATTERNS) {
            printHeader("Successful Search", pattern);
            for (int n : SIZES) {
                List<Integer> data = PerformanceComparison.generateData(n, pattern);

                long treap = PerformanceComparison.benchmarkTreapSuccessfulSearch(data, ITERATIONS);
                long avl   = PerformanceComparison.benchmarkAVLSuccessfulSearch(data, ITERATIONS);
                long java  = PerformanceComparison.benchmarkJavaTreeMapSuccessfulSearch(data, ITERATIONS);

                System.out.printf(" | %-6d | %-16.4f | %-16.4f | %-16.4f |%n",
                        n, treap/1_000_000.0, avl/1_000_000.0, java/1_000_000.0);
            }
            printFooter();
        }
    }

    @Test
    void testUnsuccessfulSearch() {
        System.out.println("\n=== Unsuccessful Search ===");
        for (String pattern : PATTERNS) {
            printHeader("Unsuccessful Search", pattern);
            for (int n : SIZES) {
                List<Integer> data = PerformanceComparison.generateData(n, pattern);

                long treap = PerformanceComparison.benchmarkTreapUnsuccessfulSearch(data, ITERATIONS);
                long avl   = PerformanceComparison.benchmarkAVLUnsuccessfulSearch(data, ITERATIONS);
                long java  = PerformanceComparison.benchmarkJavaTreeMapUnsuccessfulSearch(data, ITERATIONS);

                System.out.printf("| %-6d | %-16.4f | %-16.4f | %-16.4f |%n",
                        n, treap/1_000_000.0, avl/1_000_000.0, java/1_000_000.0);
            }
            printFooter();
        }
    }

    @Test
    void testDeletion() {
        System.out.println("\n=== Deletion ===");
        for (String pattern : PATTERNS) {
            printHeader("Deletion", pattern);
            for (int n : SIZES) {
                List<Integer> data = PerformanceComparison.generateData(n, pattern);

                long treap = PerformanceComparison.benchmarkTreapDelete(data, ITERATIONS);
                long avl   = PerformanceComparison.benchmarkAVLDelete(data, ITERATIONS);
                long java  = PerformanceComparison.benchmarkJavaTreeMapDelete(data, ITERATIONS);

                System.out.printf("| %-6d | %-16.4f | %-16.4f | %-16.4f |%n",
                        n, treap/1_000_000.0, avl/1_000_000.0, java/1_000_000.0);
            }
            printFooter();
        }
    }

    @Test
    void testTraversal() {
        System.out.println("\n=== Traversal ===");
        for (String pattern : PATTERNS) {
            printHeader("Traversal", pattern);
            for (int n : SIZES) {
                List<Integer> data = PerformanceComparison.generateData(n, pattern);

                long treap = PerformanceComparison.benchmarkTreapTraversal(data, ITERATIONS);
                long avl   = PerformanceComparison.benchmarkAVLTraversal(data, ITERATIONS);
                long java  = PerformanceComparison.benchmarkJavaTreeMapTraversal(data, ITERATIONS);

                System.out.printf("| %-6d | %-16.4f | %-16.4f | %-16.4f |%n",
                        n, treap/1_000_000.0, avl/1_000_000.0, java/1_000_000.0);
            }
            printFooter();
        }
    }
}
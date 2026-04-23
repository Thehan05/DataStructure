package project20280.tree;

import org.junit.jupiter.api.Test;
import java.util.List;


public class PerformanceComparisonTest {

    // the X-axis      
    // numbers are asking the trees to process: 
    private static final int[] SIZES = {100, 500, 1000, 2000, 5000, 10000};
    
    // These are the specific patterns of data
    private static final String[] PATTERNS = {"random", "ascending", "descending", "partial"};
    
    //Run every test 5 times and average the results so a sudden computer lag 
    private static final int ITERATIONS = 5;

    // A helper method to print the top of table so the data 
    private void printHeader(String operation, String pattern) {
        System.out.println("\n Operation: " + operation + " | Pattern: " + pattern);
        System.out.println("+--------+------------------+------------------+------------------+");
        System.out.println("|   n    |      Treap (ms)  |     AVL (ms)     |  Java TM (ms)    |");
        System.out.println("+--------+------------------+------------------+------------------+");
    }

    // A helper method to draw the bottom line of the scorecard table.
    private void printFooter() {
        System.out.println("+--------+------------------+------------------+------------------+");
    }


    //TEST 1: BATCH INSERT : how fast the trees can build themselves. 

    void testBatchInsert() {
        System.out.println("\n=== Batch Insert ===");
        for (String pattern : PATTERNS) {
            printHeader("Batch Insert", pattern);
            for (int n : SIZES) {
                // Generate the list of numbers based on current size and pattern
                List<Integer> data = PerformanceComparison.generateData(n, pattern);

                // Start the stopwatch for each tree
                long treap = PerformanceComparison.benchmarkTreapBatchInsert(data, ITERATIONS);
                long avl   = PerformanceComparison.benchmarkAVLBatchInsert(data, ITERATIONS);
                long java  = PerformanceComparison.benchmarkJavaTreeMapBatchInsert(data, ITERATIONS);

                
                // Divide by 1_000_000.0 to convert the computer's raw nanoseconds 
                System.out.printf("| %-6d | %-16.4f | %-16.4f | %-16.4f |%n",
                        n, treap/1_000_000.0, avl/1_000_000.0, java/1_000_000.0);
            }
            printFooter();
        }
    }

    // TEST 2: SUCCESSFUL SEARCH: how long it takes to find a number that we KNOW is inside the tree

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

    
    // * TEST 3: UNSUCCESSFUL SEARCH: asks the tree to find a number that doesnt exist

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

    
    // * TEST 4: DELETION: how fast the trees can safely remove nodes
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

    
    // * TEST 5: TRAVERSAL:how fast the computer can read every single number in the tree
     
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

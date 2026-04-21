
package project20280.tree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TreapSortTest {

    // TEST 1: The Random Data Pattern  
    @Test 
    void testSort() { 
        
        
        int[] arr = new int[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        
        // Assigns random priority tickets, and reads them back out
        TreapSort.sort(arr);

        // Check if sorted matches a array
        assertArrayEquals(new int[]{1, 2, 4, 5, 12, 15, 21, 23, 24, 26, 33, 35}, arr);
        
    } 


    // TEST 2: The Ascending Data Pattern
    @Test 
    void testSortedArray() { 
        
        int[] arr = new int[]{1, 2, 4, 5, 12, 15, 21, 23, 24, 26, 33, 35};

        // Make this sorted list into the Treap
        TreapSort.sort(arr);

        // Verify that the Treap didn't accidentally mess up the order while processing it
        assertArrayEquals(new int[]{1, 2, 4, 5, 12, 15, 21, 23, 24, 26, 33, 35}, arr);
        
    } 


    // TEST 3: The Descending Data Pattern
    @Test 
    void testReverseSortedArray() { 
        
        int[] arr = new int[]{35, 33, 26, 24, 23, 21, 15, 12, 5, 4, 2, 1};

        // Ask the TreapSort to fix this backwards list
        TreapSort.sort(arr);

        assertArrayEquals(new int[]{1, 2, 4, 5, 12, 15, 21, 23, 24, 26, 33, 35}, arr);
        
    } 


    // TEST 4: Duplicates
    @Test 
    void testDuplicates() { 
        
        //Repeating 5s and 2s in it
        int[] arr = new int[]{5, 2, 8, 2, 1, 9, 5, 3};

        TreapSort.sort(arr);

        assertArrayEquals(new int[]{1, 2, 2, 3, 5, 5, 8, 9}, arr);
        
    } 


    // TEST 5: Single Element 
    @Test 
    void testSingleElement() { 
        
        // Just one single number in it
        int[] arr = new int[]{7};

        TreapSort.sort(arr);

        assertArrayEquals(new int[]{7}, arr);
        
    } 


    // TEST 6: Empty Array 
    @Test 
    void testEmptyArray() { 
        
        // Empty array 
        int[] arr = new int[]{};

        TreapSort.sort(arr);

        // Verify that the output successfully remained a empty array without errors.
        assertArrayEquals(new int[]{}, arr);
        
    } 

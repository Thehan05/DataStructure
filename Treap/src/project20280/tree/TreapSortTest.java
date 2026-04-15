package project20280.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TreapSortTest {

    @Test
    void testSort() {
        int[] arr = new int[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        TreapSort.sort(arr);

        assertArrayEquals(new int[]{1, 2, 4, 5, 12, 15, 21, 23, 24, 26, 33, 35}, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = new int[]{1, 2, 4, 5, 12, 15, 21, 23, 24, 26, 33, 35};

        TreapSort.sort(arr);

        assertArrayEquals(new int[]{1, 2, 4, 5, 12, 15, 21, 23, 24, 26, 33, 35}, arr);
    }

    @Test
    void testReverseSortedArray() {
        int[] arr = new int[]{35, 33, 26, 24, 23, 21, 15, 12, 5, 4, 2, 1};

        TreapSort.sort(arr);

        assertArrayEquals(new int[]{1, 2, 4, 5, 12, 15, 21, 23, 24, 26, 33, 35}, arr);
    }

    @Test
    void testDuplicates() {
        int[] arr = new int[]{5, 2, 8, 2, 1, 9, 5, 3};

        TreapSort.sort(arr);

        assertArrayEquals(new int[]{1, 2, 2, 3, 5, 5, 8, 9}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = new int[]{7};

        TreapSort.sort(arr);

        assertArrayEquals(new int[]{7}, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = new int[]{};

        TreapSort.sort(arr);

        assertArrayEquals(new int[]{}, arr);
    }
}
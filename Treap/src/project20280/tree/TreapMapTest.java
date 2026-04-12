package project20280.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TreapMapTest {

    private TreapMap<Integer, String> treap;

    @BeforeEach
    void setUp() {
        treap = new TreapMap<>();
    }

    @Test
    void testEmptyTreap() {
        assertTrue(treap.isEmpty());
        assertEquals(0, treap.size());
    }

    @Test
    void testPut() {
        treap.put(7, "seven");
        treap.put(2, "two");
        treap.put(5, "five");
        assertEquals(3, treap.size());
        assertEquals("[" + 2 + ", " + 5 + ", " + 7 + "]", treap.toString());
    }

    @Test
    void testGet() {
        treap.put(7, "seven");
        assertEquals("seven", treap.get(7));
        assertEquals(1, treap.size());
    }

    @Test
    void testRemove() {
        treap.put(1, "one");
        treap.put(2, "two");
        treap.put(3, "three");
        treap.remove(3);
        assertEquals(2, treap.size());
    }

    @Test
    void testRemoveNonExistent() {
        treap.put(1, "one");
        assertNull(treap.remove(99));
        assertEquals(1, treap.size());
    }

    @Test
    void testRemoveAll() {
        treap.put(1, "a");
        treap.put(2, "b");
        treap.put(3, "c");

        treap.remove(1);
        treap.remove(2);
        treap.remove(3);
        assertTrue(treap.isEmpty());
    }

    @Test
    void testFirstEntry() {
        treap.put(7, "a");
        treap.put(2, "b");
        treap.put(4, "c");
        assertEquals(2, treap.firstEntry().getKey());
    }

    @Test
    void testLastEntry() {
        treap.put(7, "a");
        treap.put(2, "b");
        treap.put(4, "c");
        assertEquals(7, treap.lastEntry().getKey());
    }

    @Test
    void testHeapPropertyAfterInsertions() {
        Random rng = new Random(42);
        for(int i = 0; i < 1000; i++){
            treap.put(rng.nextInt(5000), "v" + i);
        }
        assertTrue(treap.isValidTreap());
    }

    @Test
    void testHeapPropertyAfterDeletions() {
        Random rng = new Random(42);
        for(int i = 0; i < 1000; i++){
            treap.put(rng.nextInt(5000), "v" + i);
            treap.remove(rng.nextInt(5000));
        }
        assertTrue(treap.isValidTreap());
    }

    @Test
    void testSortedInsertionStaysBalanced() {
        for (int i = 0; i < 1000; i++) {
            treap.put(i, "v" + i);
        }
        assertTrue(treap.isValidTreap());
        assertTrue(treap.height() < 80);
    }

    @Test
    void testLargeInsertAndRemove() {
        for (int i = 0; i < 5000; i++) {
            treap.put(i, "v" + i);
        }
        assertEquals(5000, treap.size());
        for (int i = 0; i < 5000; i++) {
            treap.remove(i);
        }
        assertTrue(treap.isEmpty());
    }

    @Test
    void testSubMap() {
        for(int i = 1; i <= 10; i++) {
            treap.put(i, "v" + i);
        }

        List<Integer> subKeys = new ArrayList<>();
        for(Entry<Integer, String> e : treap.subMap(3, 7)){
            subKeys.add(e.getKey());
        }
        assertEquals("[3, 4, 5, 6]", subKeys.toString());
    }

     @Test
    void testCeilingEntry() {
         treap.put(2, "a");
         treap.put(5, "b");
         treap.put(8, "c");
         assertEquals(5, treap.ceilingEntry(3).getKey());
         assertEquals(5, treap.ceilingEntry(5).getKey());
    }

    @Test
    void testFloorEntry() {
        treap.put(2, "a");
        treap.put(5, "b");
        treap.put(8, "c");
        assertEquals(5, treap.floorEntry(7).getKey());
        assertEquals(5, treap.floorEntry(5).getKey());
    }
}

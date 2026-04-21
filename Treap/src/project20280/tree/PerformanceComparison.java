package project20280.tree; 

import project20280.interfaces.Entry; 

import java.util.ArrayList; 
import java.util.Collections; 
import java.util.List; 
import java.util.Random; 

public class PerformanceComparison { 

    public static List<Integer> generateData(int n, String pattern) { // Creates a list of numbers
        List<Integer> data = new ArrayList<>(); 
        for (int i = 0; i < n; i++) { // Loops from 0 up to size n
            data.add(i); // Fills the list with numbers in order 
        } 

        Random rand = new Random(42); // Makes a random number 

        switch (pattern) { // Checks what kind of scramble pattern 
            case "random": 
                Collections.shuffle(data, rand); 

            case "ascending": 
                break; 

            case "descending":
                Collections.reverse(data); 
                break; 

            case "partial":
                int swaps = Math.max(1, n / 10); // Calculate how many times to swap 
                for (int i = 0; i < swaps; i++) { 
                    int a = rand.nextInt(n);
                    int b = rand.nextInt(n); 
                    Collections.swap(data, a, b); // Swap the numbers 
                } 
                break; 
        } 

        return data; 
    }

    // INSERTION BENCHMARKS

    public static long benchmarkTreapBatchInsert(List<Integer> data, int iterations) { // Tests how fast a Treap can insert a lot of data
        long total = 0; // Starts the total time stopwatch at 0

        for (int i = 0; i < 3; i++) { // Runs a 3time warmup 
            TreapMap<Integer, Integer> map = new TreapMap<>(); 
            for (Integer x : data) { 
                map.put(x, x); 
            }
        }

        for (int i = 0; i < iterations; i++) { 
            TreapMap<Integer, Integer> map = new TreapMap<>(); 

            long start = System.nanoTime(); // Starts the timer
            for (Integer x : data) { 
                map.put(x, x); 
            }
            long end = System.nanoTime(); 

            total += (end - start); // Adds the time it took to total
        }

        return total / iterations; // average time
    }

    public static long benchmarkAVLBatchInsert(List<Integer> data, int iterations) { // Tests how fast an AVL Tree inserts data
        long total = 0;

        for (int i = 0; i < 3; i++) { 
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>(); 
            for (Integer x : data) { // Loops through data
                map.put(x, x); 
            }
        }

        for (int i = 0; i < iterations; i++) { // The test loop
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>(); 

            long start = System.nanoTime(); 
            for (Integer x : data) { 
                map.put(x, x); 
            }
            long end = System.nanoTime(); 

            total += (end - start); // Add to total time
        }

        return total / iterations; 
    }

    public static long benchmarkJavaTreeMapBatchInsert(List<Integer> data, int iterations) { // Tests Java's builtin TreeMap insertion
        long total = 0; // Start total time at 0

        for (int i = 0; i < 3; i++) { // 3time 
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>(); 
            for (Integer x : data) { 
                map.put(x, x); 
            }
        }

        for (int i = 0; i < iterations; i++) { // test loop
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>(); // Makes fresh Java TreeMap

            long start = System.nanoTime(); 
            for (Integer x : data) { 
                map.put(x, x); // Insert into Java TreeMap
            }
            long end = System.nanoTime(); 

            total += (end - start); 
        }

        return total / iterations; 
    }

    // SUCCESSFUL SEARCH BENCHMARKS 

    public static long benchmarkTreapSuccessfulSearch(List<Integer> data, int iterations) { // Tests finding items exist in a Treap
        long total = 0; 

        for (int i = 0; i < 3; i++) { 
            TreapMap<Integer, Integer> map = new TreapMap<>(); 
            for (Integer x : data) { 
                map.put(x, x); // Fill the Treap first
            }
            for (Integer x : data) { 
                map.get(x); // Search for every item 
            }
        }

        for (int i = 0; i < iterations; i++) { //test
            TreapMap<Integer, Integer> map = new TreapMap<>(); 
            for (Integer x : data) { 
                map.put(x, x); // Fill the Treap 
            }

            long start = System.nanoTime(); // Start timer for the searching
            for (Integer x : data) { 
                map.get(x); 
            }
            long end = System.nanoTime(); 

            total += (end - start); 
        }

        return total / iterations; // Average search time
    }

    public static long benchmarkAVLSuccessfulSearch(List<Integer> data, int iterations) { // Tests finding items in an AVL Tree
        long total = 0; 

        for (int i = 0; i < 3; i++) { 
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>(); // Make AVL
            for (Integer x : data) { 
                map.put(x, x); // Fill AVL
            }
            for (Integer x : data) { 
                map.get(x); // Search AVL 
            }
        }

        for (int i = 0; i < iterations; i++) { //test
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>(); // Make AVL
            for (Integer x : data) { /
                map.put(x, x); 
            }

            long start = System.nanoTime(); 
            for (Integer x : data) { 
                map.get(x); 
            }
            long end = System.nanoTime(); 

            total += (end - start); 
        }

        return total / iterations; 
    }

    public static long benchmarkJavaTreeMapSuccessfulSearch(List<Integer> data, int iterations) { // Tests finding items in Java's TreeMap
        long total = 0; // Start time 0

        for (int i = 0; i < 3; i++) { // Warmup
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>(); 
            for (Integer x : data) { 
                map.put(x, x); // Fill it
            }
            for (Integer x : data) { 
                map.get(x); // Search it 
            }
        }

        for (int i = 0; i < iterations; i++) { 
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>(); // Make Java TreeMap
            for (Integer x : data) { 
                map.put(x, x); 
            }

            long start = System.nanoTime(); 
            for (Integer x : data) { 
                map.get(x); 
            }
            long end = System.nanoTime(); 

            total += (end - start); 
        }

        return total / iterations; // Average time
    }

    

    public static long benchmarkTreapUnsuccessfulSearch(List<Integer> data, int iterations) { // Tests searching for things that are not in the Treap
        long total = 0; // Start time 0

        for (int i = 0; i < 3; i++) { // Warm-up
            TreapMap<Integer, Integer> map = new TreapMap<>(); // Make Treap
            for (Integer x : data) {
                map.put(x, x); 
            }
            for (int x = data.size(); x < 2 * data.size(); x++) { // Creates  new numbers that are high
                map.get(x); 
            }
        }

        for (int i = 0; i < iterations; i++) { 
            TreapMap<Integer, Integer> map = new TreapMap<>(); 
            for (Integer x : data) { 
                map.put(x, x);
            }

            long start = System.nanoTime(); 
            for (int x = data.size(); x < 2 * data.size(); x++) { // Create the fake, missing numbers again
                map.get(x); 
            }
            long end = System.nanoTime(); 

            total += (end - start);
        }

        return total / iterations; 
    }

    public static long benchmarkAVLUnsuccessfulSearch(List<Integer> data, int iterations) { // Tests searching for missing things in AVL
        long total = 0; // Start time 0

        for (int i = 0; i < 3; i++) { 
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>(); 
            for (Integer x : data) { // Fill AVL
                map.put(x, x);
            }
            for (int x = data.size(); x < 2 * data.size(); x++) { // Fake numbers
                map.get(x); // Search AVL 
            }
        }

        for (int i = 0; i < iterations; i++) { // test
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>(); // Make AVL
            for (Integer x : data) { // Fill AVL
                map.put(x, x);
            }

            long start = System.nanoTime(); // Start timer
            for (int x = data.size(); x < 2 * data.size(); x++) { // Fake numbers
                map.get(x); // Search AVL
            }
            long end = System.nanoTime(); // Stop timer

            total += (end - start); // Add time
        }

        return total / iterations; // Average time
    }

    public static long benchmarkJavaTreeMapUnsuccessfulSearch(List<Integer> data, int iterations) { // Tests searching for missing things in Java TreeMap
        long total = 0; // Start time 0

        for (int i = 0; i < 3; i++) { 
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>(); // Make Java Tree
            for (Integer x : data) { // Fill it
                map.put(x, x);
            }
            for (int x = data.size(); x < 2 * data.size(); x++) { // Fake numbers
                map.get(x); // Search it 
            }
        }

        for (int i = 0; i < iterations; i++) { // test
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>(); // Make Java Tree
            for (Integer x : data) { // Fill it
                map.put(x, x);
            }

            long start = System.nanoTime(); // Start timer
            for (int x = data.size(); x < 2 * data.size(); x++) { // Fake numbers
                map.get(x); // Search it
            }
            long end = System.nanoTime(); 

            total += (end - start); // Add time
        }

        return total / iterations; 
    }

    //DELETION BENCHMARKS 

    public static long benchmarkTreapDelete(List<Integer> data, int iterations) { // Tests how fast a Treap can delete items
        long total = 0; // Start time 0

        for (int i = 0; i < 3; i++) { 
            TreapMap<Integer, Integer> map = new TreapMap<>(); // Make Treap
            for (Integer x : data) { 
                map.put(x, x);
            }
            for (Integer x : data) { 
                map.remove(x); // Delete every single item 
            }
        }

        for (int i = 0; i < iterations; i++) { // test
            TreapMap<Integer, Integer> map = new TreapMap<>(); // Make Treap
            for (Integer x : data) { // Fill it up 
                map.put(x, x);
            }

            long start = System.nanoTime(); 
            for (Integer x : data) { 
                map.remove(x); // Delete the items one by one
            }
            long end = System.nanoTime(); 

            total += (end - start); // Add time
        }

        return total / iterations; 
    }

    public static long benchmarkAVLDelete(List<Integer> data, int iterations) { // Tests AVL Tree deletions
        long total = 0; // Start time 0

        for (int i = 0; i < 3; i++) { 
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>(); // Make AVL
            for (Integer x : data) { // Fill it
                map.put(x, x);
            }
            for (Integer x : data) { 
                map.remove(x); 
            }
        }

        for (int i = 0; i < iterations; i++) { 
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>(); // Make AVL
            for (Integer x : data) { // Fill it
                map.put(x, x);
            }

            long start = System.nanoTime(); 
            for (Integer x : data) { 
                map.remove(x); 
            }
            long end = System.nanoTime(); 

            total += (end - start); 
        }

        return total / iterations; // Average time
    }

    public static long benchmarkJavaTreeMapDelete(List<Integer> data, int iterations) { // Tests Java TreeMap deletions
        long total = 0; // Start time 0

        for (int i = 0; i < 3; i++) { 
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>(); // Make Java Tree
            for (Integer x : data) { // Fill it
                map.put(x, x);
            }
            for (Integer x : data) { 
                map.remove(x); 
            }
        }

        for (int i = 0; i < iterations; i++) { 
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>(); // Make Java Tree
            for (Integer x : data) { // Fill it
                map.put(x, x);
            }

            long start = System.nanoTime(); 
            for (Integer x : data) { 
                map.remove(x);
            } 
            long end = System.nanoTime(); // Stop timer

            total += (end - start); 
        }

        return total / iterations; 
    }


    public static long benchmarkTreapTraversal(List<Integer> data, int iterations) { // Tests how fast we can read every single item in a Treap
        long total = 0; // Start time 0

        for (int i = 0; i < 3; i++) { 
            TreapMap<Integer, Integer> map = new TreapMap<>(); // Make Treap
            for (Integer x : data) { 
                map.put(x, x);
            }
            for (Entry<Integer, Integer> e : map.entrySet()) { // Ask the Treap to list all its contents
                int k = e.getKey(); //look at each key to prove visited it 
            }
        }

        for (int i = 0; i < iterations; i++) { 
            TreapMap<Integer, Integer> map = new TreapMap<>(); // Make Treap
            for (Integer x : data) { // Fill it
                map.put(x, x);
            }

            long start = System.nanoTime(); 
            for (Entry<Integer, Integer> e : map.entrySet()) { // Loop through the entire list of contents inside the tree
                int k = e.getKey(); 
            }
            long end = System.nanoTime(); 

            total += (end - start); 
        }

        return total / iterations; 
    }

    public static long benchmarkAVLTraversal(List<Integer> data, int iterations) { // Tests reading through an AVL Tree
        long total = 0; // Start time 0

        for (int i = 0; i < 3; i++) { 
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>(); // Make AVL
            for (Integer x : data) { // Fill it
                map.put(x, x);
            }
            for (Entry<Integer, Integer> e : map.entrySet()) { 
                int k = e.getKey(); 
            }
        }

        for (int i = 0; i < iterations; i++) { 
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>(); // Make AVL
            for (Integer x : data) { 
                map.put(x, x);
            }

            long start = System.nanoTime(); // Start timer
            for (Entry<Integer, Integer> e : map.entrySet()) { 
                int k = e.getKey(); // Look at key
            }
            long end = System.nanoTime(); 

            total += (end - start); 
        }

        return total / iterations; 
    }

    public static long benchmarkJavaTreeMapTraversal(List<Integer> data, int iterations) { // Tests reading through a Java TreeMap
        long total = 0; // Start time 0

        for (int i = 0; i < 3; i++) { 
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>(); // Make Java Tree
            for (Integer x : data) { // Fill it
                map.put(x, x);
            }
            for (java.util.Map.Entry<Integer, Integer> e : map.entrySet()) { 
                int k = e.getKey(); // Look at key
            }
        }

        for (int i = 0; i < iterations; i++) { 
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>(); // Make Java Tree
            for (Integer x : data) { 
            }

            long start = System.nanoTime(); 
            for (java.util.Map.Entry<Integer, Integer> e : map.entrySet()) { // Loop contents
                int k = e.getKey(); 
            }
            long end = System.nanoTime();

            total += (end - start); // Add time
        }

        return total / iterations; // Average time
    }

} 

package project20280.tree;

import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PerformanceComparison {

    public static List<Integer> generateData(int n, String pattern) {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            data.add(i);
        }

        Random rand = new Random(42);

        switch (pattern) {
            case "random":
                Collections.shuffle(data, rand);
                break;

            case "ascending":
                break;

            case "descending":
                Collections.reverse(data);
                break;

            case "partial":
                int swaps = Math.max(1, n / 10);
                for (int i = 0; i < swaps; i++) {
                    int a = rand.nextInt(n);
                    int b = rand.nextInt(n);
                    Collections.swap(data, a, b);
                }
                break;
        }

        return data;
    }

    public static long benchmarkTreapBatchInsert(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            TreapMap<Integer, Integer> map = new TreapMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
        }

        for (int i = 0; i < iterations; i++) {
            TreapMap<Integer, Integer> map = new TreapMap<>();

            long start = System.nanoTime();
            for (Integer x : data) {
                map.put(x, x);
            }
            long end = System.nanoTime();

            total += (end - start);
        }

        return total / iterations;
    }

    public static long benchmarkAVLBatchInsert(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
        }

        for (int i = 0; i < iterations; i++) {
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>();

            long start = System.nanoTime();
            for (Integer x : data) {
                map.put(x, x);
            }
            long end = System.nanoTime();

            total += (end - start);
        }

        return total / iterations;
    }

    public static long benchmarkJavaTreeMapBatchInsert(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
        }

        for (int i = 0; i < iterations; i++) {
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>();

            long start = System.nanoTime();
            for (Integer x : data) {
                map.put(x, x);
            }
            long end = System.nanoTime();

            total += (end - start);
        }

        return total / iterations;
    }

    public static long benchmarkTreapSuccessfulSearch(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            TreapMap<Integer, Integer> map = new TreapMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
            for (Integer x : data) {
                map.get(x);
            }
        }

        for (int i = 0; i < iterations; i++) {
            TreapMap<Integer, Integer> map = new TreapMap<>();
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

        return total / iterations;
    }

    public static long benchmarkAVLSuccessfulSearch(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
            for (Integer x : data) {
                map.get(x);
            }
        }

        for (int i = 0; i < iterations; i++) {
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>();
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

        return total / iterations;
    }

    public static long benchmarkJavaTreeMapSuccessfulSearch(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
            for (Integer x : data) {
                map.get(x);
            }
        }

        for (int i = 0; i < iterations; i++) {
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>();
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

        return total / iterations;
    }

    public static long benchmarkTreapUnsuccessfulSearch(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            TreapMap<Integer, Integer> map = new TreapMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
            for (int x = data.size(); x < 2 * data.size(); x++) {
                map.get(x);
            }
        }

        for (int i = 0; i < iterations; i++) {
            TreapMap<Integer, Integer> map = new TreapMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }

            long start = System.nanoTime();
            for (int x = data.size(); x < 2 * data.size(); x++) {
                map.get(x);
            }
            long end = System.nanoTime();

            total += (end - start);
        }

        return total / iterations;
    }

    public static long benchmarkAVLUnsuccessfulSearch(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
            for (int x = data.size(); x < 2 * data.size(); x++) {
                map.get(x);
            }
        }

        for (int i = 0; i < iterations; i++) {
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }

            long start = System.nanoTime();
            for (int x = data.size(); x < 2 * data.size(); x++) {
                map.get(x);
            }
            long end = System.nanoTime();

            total += (end - start);
        }

        return total / iterations;
    }

    public static long benchmarkJavaTreeMapUnsuccessfulSearch(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
            for (int x = data.size(); x < 2 * data.size(); x++) {
                map.get(x);
            }
        }

        for (int i = 0; i < iterations; i++) {
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }

            long start = System.nanoTime();
            for (int x = data.size(); x < 2 * data.size(); x++) {
                map.get(x);
            }
            long end = System.nanoTime();

            total += (end - start);
        }

        return total / iterations;
    }

    public static long benchmarkTreapDelete(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            TreapMap<Integer, Integer> map = new TreapMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
            for (Integer x : data) {
                map.remove(x);
            }
        }

        for (int i = 0; i < iterations; i++) {
            TreapMap<Integer, Integer> map = new TreapMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }

            long start = System.nanoTime();
            for (Integer x : data) {
                map.remove(x);
            }
            long end = System.nanoTime();

            total += (end - start);
        }

        return total / iterations;
    }

    public static long benchmarkAVLDelete(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
            for (Integer x : data) {
                map.remove(x);
            }
        }

        for (int i = 0; i < iterations; i++) {
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }

            long start = System.nanoTime();
            for (Integer x : data) {
                map.remove(x);
            }
            long end = System.nanoTime();

            total += (end - start);
        }

        return total / iterations;
    }

    public static long benchmarkJavaTreeMapDelete(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
            for (Integer x : data) {
                map.remove(x);
            }
        }

        for (int i = 0; i < iterations; i++) {
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }

            long start = System.nanoTime();
            for (Integer x : data) {
                map.remove(x);
            }
            long end = System.nanoTime();

            total += (end - start);
        }

        return total / iterations;
    }

    public static long benchmarkTreapTraversal(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            TreapMap<Integer, Integer> map = new TreapMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
            for (Entry<Integer, Integer> e : map.entrySet()) {
                int k = e.getKey();
            }
        }

        for (int i = 0; i < iterations; i++) {
            TreapMap<Integer, Integer> map = new TreapMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }

            long start = System.nanoTime();
            for (Entry<Integer, Integer> e : map.entrySet()) {
                int k = e.getKey();
            }
            long end = System.nanoTime();

            total += (end - start);
        }

        return total / iterations;
    }

    public static long benchmarkAVLTraversal(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
            for (Entry<Integer, Integer> e : map.entrySet()) {
                int k = e.getKey();
            }
        }

        for (int i = 0; i < iterations; i++) {
            AVLTreeMap<Integer, Integer> map = new AVLTreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }

            long start = System.nanoTime();
            for (Entry<Integer, Integer> e : map.entrySet()) {
                int k = e.getKey();
            }
            long end = System.nanoTime();

            total += (end - start);
        }

        return total / iterations;
    }

    public static long benchmarkJavaTreeMapTraversal(List<Integer> data, int iterations) {
        long total = 0;

        for (int i = 0; i < 3; i++) {
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }
            for (java.util.Map.Entry<Integer, Integer> e : map.entrySet()) {
                int k = e.getKey();
            }
        }

        for (int i = 0; i < iterations; i++) {
            java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>();
            for (Integer x : data) {
                map.put(x, x);
            }

            long start = System.nanoTime();
            for (java.util.Map.Entry<Integer, Integer> e : map.entrySet()) {
                int k = e.getKey();
            }
            long end = System.nanoTime();

            total += (end - start);
        }

        return total / iterations;
    }

}
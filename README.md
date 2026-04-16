# COMP20280 - Data Structre Project : Treap Assignment
### Group Members
Thehan Arunadewa, Robbie Devilly, Dermot Yang, Li WeiHao
## Overview

In this project we are implementing a Treap , which is a data structure that combines a Binary Search Tree (BST) and a Heap. Each node of a treap has a key (for ordering like a BST) and a random priority (to maintain balance like a heap).
This randomness helps keep the tree balanced on average, allowing operations like insert, delete and search to run in O(log n) time without complex balancing rules like AVL trees.

We also benchmarked the Treap's performance against an AVL tree and Java's TreeMap, and tests how well it works against other sorting algorithms like Quicksort, MergeSort, PQSort and TimSort.

---
 
## Project Structure
 
```
project20280/
├── interfaces/        # ADTs: Map, SortedMap, Tree, PriorityQueue, etc.  [provided]
├── priorityqueue/     # DefaultComparator                                 [provided]
├── sorting/           # MergeSort, QuickSort, PQSort, Benchmark          [implemented]
│                      # sortingTest                                       [implemented]
└── tree/              # AbstractTree, LinkedBinaryTree, TreeMap, etc.    [provided]
                       # TreapMap, TreapMapTest                           [implemented]
                       # TreapSort, TreapSortTest                         [implemented]
                       # PerformanceComparison, PerformanceComparisonTest [implemented]
```
 
---

## Performance Summary
 
All three map structures were benchmarked across input sizes n = [100, ..., 10000] and four input patterns: random, ascending, descending, and partially sorted.

### Key findings
 
- All three maps perform comparably on random input.
- Treap handles sorted input well — random priorities prevent degenerate tree shapes regardless of insertion order.
- TreapSort and PQSort are O(n log n) but slower in practice than QuickSort and TimSort due to higher constant overhead.
> Full charts and discussion are included in the group report.
 
---

### Build & Run
 
```bash
# Compile
javac -d out -sourcepath src src/tree/TreapMap.java
 
# Run tests
# Open TreapMapTest.java in your IDE and run as JUnit test
 
# Run benchmarks
# Open PerformanceComparison.java or sortingTest.java and run as Java application
```

### Running Benchmarks
 
The `PerformanceComparison` class runs timed trials for insertion, search, deletion, and traversal across all three map structures.
 
The `sortingTest` / `Benchmark` class compares all sorting algorithms across input sizes and patterns.
 
---

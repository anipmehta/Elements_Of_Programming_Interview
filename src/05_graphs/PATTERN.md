# Graphs

## Description

Problems involving traversal, connectivity, shortest paths, and ordering on general graphs (directed or undirected). Graph problems require choosing the right traversal strategy and recognizing structural properties like cycles, components, and DAGs.

## When to Apply

- The input is a grid, adjacency list, or edge list
- The problem asks about connectivity, reachability, or shortest distance
- There are dependency/ordering constraints (prerequisites, build order)
- You need to detect cycles or find connected components

## Complexity Characteristics

| Operation | Time | Space |
|---|---|---|
| DFS / BFS traversal | O(V + E) | O(V) |
| Topological sort | O(V + E) | O(V) |
| Dijkstra (min-heap) | O((V + E) log V) | O(V) |
| Union-Find (with rank + path compression) | O(α(n)) per op | O(V) |

## Sub-Patterns

### DFS / BFS
General graph traversal for flood-fill, island counting, maze solving, and reachability. DFS uses a stack (or recursion), BFS uses a queue. BFS gives shortest path in unweighted graphs.

### Topological Sort
Linear ordering of vertices in a DAG such that every edge u→v has u before v. Use Kahn's algorithm (BFS with in-degree) or DFS post-order reversal. Classic for course scheduling and build dependencies.

### Union-Find
Disjoint set data structure for dynamic connectivity queries. Use when you need to merge groups and check if two elements belong to the same component. Efficient for "number of connected components" and MST problems.

### Shortest Path
Weighted graph distance problems. Dijkstra for non-negative weights, Bellman-Ford for negative edges. Often combined with priority queues.

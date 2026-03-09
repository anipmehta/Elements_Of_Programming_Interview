# Design

## Description

Problems that ask you to design a data structure or system with specific API methods and performance guarantees. These test your ability to combine multiple data structures (hash maps, linked lists, arrays, heaps) to meet time/space constraints.

## When to Apply

- The problem says "Design a class that supports..." or "Implement the following API"
- Multiple operations must each run in O(1) or O(log n)
- You need to combine data structures (e.g., hash map + doubly linked list for LRU)
- The problem simulates a real system (cache, game, snapshot, random access)

## Complexity Characteristics

| Pattern | Time per Operation | Space |
|---|---|---|
| LRU Cache (map + DLL) | O(1) get/put | O(capacity) |
| Snapshot Array | O(log S) get, O(1) set | O(sets) |
| Randomized structure | O(1) insert/delete/random | O(n) |

Where S = number of snapshots.

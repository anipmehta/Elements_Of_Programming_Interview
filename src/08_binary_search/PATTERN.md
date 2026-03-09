# Binary Search

## Description

Divide the search space in half at each step to find a target or boundary in O(log n) time. Beyond sorted arrays, binary search applies to any monotonic predicate — "find the first/last position where a condition holds."

## When to Apply

- The input is sorted or the answer space is monotonic
- The problem asks for "first position", "last position", or "minimum value satisfying X"
- You can define a predicate that partitions the search space into true/false regions
- Brute-force linear scan is too slow and the search space can be halved

## Complexity Characteristics

| Operation | Time | Space |
|---|---|---|
| Standard binary search | O(log n) | O(1) |
| Binary search on answer | O(log(range) × verify) | O(1) |

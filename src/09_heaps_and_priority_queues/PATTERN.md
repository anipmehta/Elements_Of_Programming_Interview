# Heaps and Priority Queues

## Description

Problems requiring efficient access to the minimum or maximum element in a dynamic collection. A heap (usually a binary min-heap or max-heap) supports insert and extract-min/max in O(log n), making it ideal for top-K, merge, and scheduling problems.

## When to Apply

- The problem asks for the K largest/smallest/closest elements
- You need to merge K sorted sequences efficiently
- A greedy strategy requires always picking the current best candidate
- The problem involves scheduling, intervals, or streaming data with priority

## Complexity Characteristics

| Operation | Time | Space |
|---|---|---|
| Insert | O(log n) | O(n) |
| Extract min/max | O(log n) | O(n) |
| Peek min/max | O(1) | O(n) |
| Build heap from array | O(n) | O(n) |
| Top-K via heap of size K | O(n log K) | O(K) |

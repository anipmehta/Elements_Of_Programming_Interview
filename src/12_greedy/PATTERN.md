# Greedy

## Description

Problems where making the locally optimal choice at each step leads to a globally optimal solution. Greedy algorithms are simpler than DP but only work when the problem has the greedy-choice property — you must prove (or intuit) that no backtracking is needed.

## When to Apply

- The problem asks for a minimum/maximum and local decisions don't conflict with global optimality
- Sorting the input by some criterion reveals a natural processing order
- An exchange argument shows that swapping any two choices can't improve the result
- The problem involves intervals, scheduling, or frequency-based reduction

## Complexity Characteristics

| Operation | Time | Space |
|---|---|---|
| Sort + single pass | O(n log n) | O(1) |
| Frequency counting + greedy | O(n) | O(k) |
| Interval scheduling | O(n log n) | O(1) |

Where k = alphabet or category size.

# Dynamic Programming

## Description

Problems with overlapping subproblems and optimal substructure. DP avoids redundant computation by storing results of subproblems. The two main approaches are top-down (memoization) and bottom-up (tabulation).

## When to Apply

- The problem asks for an optimal value (min cost, max profit, number of ways)
- A brute-force recursive solution has exponential time due to repeated subproblems
- The problem can be broken into smaller instances of itself
- Greedy doesn't work because local choices don't guarantee global optimality

## Complexity Characteristics

| Operation | Time | Space |
|---|---|---|
| 1D DP | O(n) | O(n) or O(1) with rolling |
| 2D DP | O(n × m) | O(n × m) or O(m) with rolling |
| Bitmask DP | O(2^n × n) | O(2^n) |

## Sub-Patterns

### Memoization (Top-Down)
Start from the original problem, recurse into subproblems, and cache results in a hash map or array. Natural when the recursive structure is clear but not all subproblems are needed.

### Bottom-Up (Tabulation)
Fill a table iteratively from base cases to the final answer. Often more space-efficient (rolling arrays) and avoids recursion stack overhead. Preferred when all subproblems must be solved.

### DP on Trees
Combine DP with tree traversal. Compute subtree answers via post-order DFS, then combine children's results at each node. Used for tree diameter, max path sum, and house robber on trees.

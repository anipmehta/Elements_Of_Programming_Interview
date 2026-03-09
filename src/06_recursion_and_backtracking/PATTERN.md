# Recursion and Backtracking

## Description

Problems that explore a decision space by making choices, recursing, and undoing choices when they lead to dead ends. Backtracking prunes invalid branches early, making brute-force search feasible for constrained problems.

## When to Apply

- The problem asks for all combinations, permutations, or subsets
- You need to place items under constraints (N-Queens, Sudoku)
- The search space is exponential but can be pruned with validity checks
- The problem says "find all valid..." or "generate all..."

## Complexity Characteristics

| Operation | Time | Space |
|---|---|---|
| Subsets / power set | O(2^n) | O(n) stack |
| Permutations | O(n!) | O(n) stack |
| Constraint backtracking | O(b^d) pruned | O(d) stack |

Where b = branching factor, d = depth.

## Sub-Patterns

### Subsets and Permutations
Enumerate all subsets or orderings of a collection. Use include/exclude decisions for subsets, swap-based or used-array approaches for permutations.

### Constraint-Based
Place elements on a board or structure while satisfying rules. At each step, try a candidate, check constraints, recurse if valid, backtrack if not. N-Queens, Sudoku, and word search are canonical examples.

# Arrays and Strings

## Description

Foundational pattern covering in-place array manipulation, string parsing, hashing, and frequency counting. Most interview problems start here — mastering these techniques builds intuition for every other pattern.

## When to Apply

- The input is an array or string and the problem asks to transform, search, or aggregate it
- You need to find duplicates, intersections, or frequency-based results
- The problem involves character-level manipulation (anagrams, palindromes, encoding)
- Constraints suggest O(n) or O(n log n) solutions

## Complexity Characteristics

| Operation | Time | Space |
|---|---|---|
| Hash map lookup/insert | O(1) avg | O(n) |
| Sorting-based approach | O(n log n) | O(1)–O(n) |
| Single pass with pointers | O(n) | O(1) |

## Sub-Patterns

### Two Pointers
Use two indices moving toward each other (or in the same direction) to reduce a nested loop to a single pass. Classic for sorted arrays, partitioning, and pair-sum problems.

### Sliding Window
Maintain a dynamic window over a contiguous subarray/substring. Expand the right boundary, shrink the left when a constraint is violated. Ideal for "longest/shortest substring with condition" problems.

### Prefix Sum
Precompute cumulative sums so any subarray sum can be answered in O(1). Useful for range queries, subarray sum equals K, and block-distance problems.

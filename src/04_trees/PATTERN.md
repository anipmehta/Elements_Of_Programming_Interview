# Trees

## Description

Problems on binary trees, BSTs, and n-ary trees. Tree problems test recursive thinking, level-aware processing, and the ability to propagate information up or down a tree structure.

## When to Apply

- The input is a tree (binary, BST, or n-ary)
- The problem asks for path sums, ancestor queries, or level-order results
- You need to serialize/deserialize or construct a tree from traversal data
- Recursive decomposition into left/right subtrees simplifies the problem

## Complexity Characteristics

| Operation | Time | Space |
|---|---|---|
| DFS traversal | O(n) | O(h) stack |
| BFS traversal | O(n) | O(w) queue |
| BST search | O(h) | O(1) iter / O(h) rec |

Where h = tree height, w = max level width.

## Sub-Patterns

### DFS (Depth-First Search)
Preorder, inorder, or postorder traversal. Use when you need to explore full root-to-leaf paths, compute subtree properties, or propagate constraints downward. Most LCA and path-sum problems fall here.

### BFS (Breadth-First Search)
Level-order traversal using a queue. Use when the problem is level-aware (zigzag, right-side view, level averages) or asks for the shortest path in an unweighted tree.

### Construction
Building a tree from serialized data, traversal arrays, or other representations. Requires understanding how traversal orders uniquely determine tree structure.

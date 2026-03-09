# Stacks and Queues

## Description

Problems that leverage LIFO (stack) or FIFO (queue) ordering to process elements in a specific sequence. Stacks excel at matching, nesting, and nearest-greater/smaller element problems. Queues shine in BFS and scheduling.

## When to Apply

- The problem involves matching or validating nested structures (parentheses, tags)
- You need the "next greater/smaller element" for each position
- Expression evaluation or path simplification is required
- A sliding window needs to track min/max efficiently

## Complexity Characteristics

| Operation | Time | Space |
|---|---|---|
| Push / Pop / Peek | O(1) | O(n) |
| Monotonic stack pass | O(n) | O(n) |
| Expression evaluation | O(n) | O(n) |

## Sub-Patterns

### Basic
Standard stack usage for parenthesis matching, expression parsing, and path simplification. Push when opening, pop when closing or reducing.

### Monotonic Stack
Maintain a stack where elements are always increasing or decreasing. Enables O(n) solutions for "next greater element", "largest rectangle", and sliding window max/min problems.

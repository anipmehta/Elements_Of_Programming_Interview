import { describe, it, expect } from "vitest";
import { formatPattern, patternDocUrl } from "../../lib/format";

describe("formatPattern", () => {
  it("strips numeric prefix and title-cases", () => {
    expect(formatPattern("07_dynamic_programming")).toBe("Dynamic Programming");
  });

  it("uses ampersand for known pairs", () => {
    expect(formatPattern("01_arrays_and_strings")).toBe("Arrays & Strings");
    expect(formatPattern("03_stacks_and_queues")).toBe("Stacks & Queues");
    expect(formatPattern("09_heaps_and_priority_queues")).toBe("Heaps & Priority Queues");
    expect(formatPattern("06_recursion_and_backtracking")).toBe("Recursion & Backtracking");
  });

  it("uppercases known tokens like DFS, BFS", () => {
    expect(formatPattern("dfs_bfs")).toBe("DFS BFS");
    expect(formatPattern("dfs")).toBe("DFS");
    expect(formatPattern("bfs")).toBe("BFS");
  });

  it("formats sub-patterns without numeric prefix", () => {
    expect(formatPattern("sliding_window")).toBe("Sliding Window");
    expect(formatPattern("two_pointers")).toBe("Two Pointers");
    expect(formatPattern("monotonic_stack")).toBe("Monotonic Stack");
    expect(formatPattern("topological_sort")).toBe("Topological Sort");
    expect(formatPattern("bottom_up")).toBe("Bottom Up");
    expect(formatPattern("memoization")).toBe("Memoization");
    expect(formatPattern("constraint_based")).toBe("Constraint Based");
    expect(formatPattern("prefix_sum")).toBe("Prefix Sum");
    expect(formatPattern("shortest_path")).toBe("Shortest Path");
  });

  it("handles single-word patterns", () => {
    expect(formatPattern("12_greedy")).toBe("Greedy");
    expect(formatPattern("basic")).toBe("Basic");
  });
});

describe("patternDocUrl", () => {
  it("builds correct GitHub URL for pattern", () => {
    expect(patternDocUrl("01_arrays_and_strings")).toBe(
      "https://github.com/anipmehta/AlgoForge/blob/master/src/01_arrays_and_strings/PATTERN.md"
    );
  });

  it("builds correct URL for another pattern", () => {
    expect(patternDocUrl("07_dynamic_programming")).toBe(
      "https://github.com/anipmehta/AlgoForge/blob/master/src/07_dynamic_programming/PATTERN.md"
    );
  });
});

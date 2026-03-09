import { describe, it, expect } from "vitest";
import { computeOverall, computeByDifficulty, computeByPattern } from "../../lib/stats";
import type { ProblemEntry } from "../../lib/types";

function makeProblem(overrides: Partial<ProblemEntry> = {}): ProblemEntry {
  return {
    name: "test",
    pattern: "01_arrays_and_strings",
    subPattern: null,
    difficulty: "Medium",
    companies: [],
    path: "src/01_arrays_and_strings/test",
    sourceUrl: "TBD",
    ...overrides,
  };
}

const problems: ProblemEntry[] = [
  makeProblem({ name: "a", pattern: "01_arrays_and_strings", difficulty: "Easy" }),
  makeProblem({ name: "b", pattern: "01_arrays_and_strings", difficulty: "Medium" }),
  makeProblem({ name: "c", pattern: "02_linked_lists", difficulty: "Hard" }),
  makeProblem({ name: "d", pattern: "02_linked_lists", difficulty: "Easy" }),
  makeProblem({ name: "e", pattern: "03_sorting_and_searching", difficulty: "Medium" }),
];

describe("computeOverall", () => {
  it("returns zero for empty problems", () => {
    const result = computeOverall([], new Set());
    expect(result).toEqual({ total: 0, completed: 0, percentage: 0 });
  });

  it("returns zero when nothing completed", () => {
    const result = computeOverall(problems, new Set());
    expect(result).toEqual({ total: 5, completed: 0, percentage: 0 });
  });

  it("computes correct percentage", () => {
    const result = computeOverall(problems, new Set(["a", "c"]));
    expect(result).toEqual({ total: 5, completed: 2, percentage: 40 });
  });

  it("returns 100% when all completed", () => {
    const result = computeOverall(problems, new Set(["a", "b", "c", "d", "e"]));
    expect(result).toEqual({ total: 5, completed: 5, percentage: 100 });
  });

  it("ignores completed names not in problems", () => {
    const result = computeOverall(problems, new Set(["a", "zzz"]));
    expect(result.completed).toBe(1);
  });
});

describe("computeByDifficulty", () => {
  it("returns stats for each difficulty", () => {
    const result = computeByDifficulty(problems, new Set(["a", "c"]));
    expect(result).toHaveLength(3);
    expect(result[0]).toEqual({ label: "Easy", total: 2, completed: 1, percentage: 50 });
    expect(result[1]).toEqual({ label: "Medium", total: 2, completed: 0, percentage: 0 });
    expect(result[2]).toEqual({ label: "Hard", total: 1, completed: 1, percentage: 100 });
  });

  it("handles empty problems", () => {
    const result = computeByDifficulty([], new Set());
    expect(result).toHaveLength(3);
    result.forEach((r) => {
      expect(r.total).toBe(0);
      expect(r.completed).toBe(0);
      expect(r.percentage).toBe(0);
    });
  });
});

describe("computeByPattern", () => {
  it("returns sorted stats per pattern", () => {
    const result = computeByPattern(problems, new Set(["a", "b"]));
    expect(result).toHaveLength(3);
    expect(result[0]).toEqual({ label: "arrays and strings", total: 2, completed: 2, percentage: 100 });
    expect(result[1]).toEqual({ label: "linked lists", total: 2, completed: 0, percentage: 0 });
    expect(result[2]).toEqual({ label: "sorting and searching", total: 1, completed: 0, percentage: 0 });
  });

  it("handles empty problems", () => {
    const result = computeByPattern([], new Set());
    expect(result).toHaveLength(0);
  });

  it("strips numeric prefix from pattern labels", () => {
    const result = computeByPattern(problems, new Set());
    expect(result[0].label).toBe("arrays and strings");
  });
});

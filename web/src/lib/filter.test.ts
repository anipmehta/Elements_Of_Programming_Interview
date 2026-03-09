import { describe, it, expect } from "vitest";
import { applyFilters, extractPatterns, extractCompanies } from "./filter";
import type { ProblemEntry, FilterState } from "./types";

function makeProblem(overrides: Partial<ProblemEntry> = {}): ProblemEntry {
  return {
    name: "test_problem",
    pattern: "01_arrays_and_strings",
    subPattern: null,
    difficulty: "Medium",
    companies: [],
    path: "src/01_arrays_and_strings/test_problem",
    sourceUrl: "https://leetcode.com/problems/test",
    ...overrides,
  };
}

const problems: ProblemEntry[] = [
  makeProblem({ name: "two_sum", pattern: "01_arrays_and_strings", difficulty: "Easy", companies: ["Google", "Amazon"] }),
  makeProblem({ name: "lru_cache", pattern: "02_linked_lists", difficulty: "Medium", companies: ["Amazon"] }),
  makeProblem({ name: "binary_search", pattern: "03_sorting_and_searching", difficulty: "Easy", companies: ["Google"] }),
  makeProblem({ name: "merge_intervals", pattern: "01_arrays_and_strings", difficulty: "Hard", companies: [] }),
];

describe("applyFilters", () => {
  it("returns all problems when all filters are null", () => {
    const filters: FilterState = { pattern: null, difficulty: null, company: null };
    expect(applyFilters(problems, filters)).toEqual(problems);
  });

  it("filters by pattern", () => {
    const filters: FilterState = { pattern: "01_arrays_and_strings", difficulty: null, company: null };
    const result = applyFilters(problems, filters);
    expect(result).toHaveLength(2);
    expect(result.every((p) => p.pattern === "01_arrays_and_strings")).toBe(true);
  });

  it("filters by difficulty", () => {
    const filters: FilterState = { pattern: null, difficulty: "Easy", company: null };
    const result = applyFilters(problems, filters);
    expect(result).toHaveLength(2);
    expect(result.every((p) => p.difficulty === "Easy")).toBe(true);
  });

  it("filters by company", () => {
    const filters: FilterState = { pattern: null, difficulty: null, company: "Google" };
    const result = applyFilters(problems, filters);
    expect(result).toHaveLength(2);
    expect(result.every((p) => p.companies.includes("Google"))).toBe(true);
  });

  it("applies AND logic across multiple filters", () => {
    const filters: FilterState = { pattern: "01_arrays_and_strings", difficulty: "Easy", company: "Google" };
    const result = applyFilters(problems, filters);
    expect(result).toHaveLength(1);
    expect(result[0].name).toBe("two_sum");
  });

  it("returns empty array when no problems match", () => {
    const filters: FilterState = { pattern: "01_arrays_and_strings", difficulty: "Medium", company: "Google" };
    expect(applyFilters(problems, filters)).toEqual([]);
  });

  it("returns empty array for empty input", () => {
    const filters: FilterState = { pattern: null, difficulty: null, company: null };
    expect(applyFilters([], filters)).toEqual([]);
  });
});

describe("extractPatterns", () => {
  it("returns sorted unique patterns", () => {
    expect(extractPatterns(problems)).toEqual([
      "01_arrays_and_strings",
      "02_linked_lists",
      "03_sorting_and_searching",
    ]);
  });

  it("returns empty array for empty input", () => {
    expect(extractPatterns([])).toEqual([]);
  });

  it("deduplicates patterns", () => {
    const dupes = [makeProblem({ pattern: "a" }), makeProblem({ pattern: "a" })];
    expect(extractPatterns(dupes)).toEqual(["a"]);
  });
});

describe("extractCompanies", () => {
  it("returns sorted unique companies", () => {
    expect(extractCompanies(problems)).toEqual(["Amazon", "Google"]);
  });

  it("returns empty array when no companies exist", () => {
    const noCompanies = [makeProblem({ companies: [] })];
    expect(extractCompanies(noCompanies)).toEqual([]);
  });

  it("returns empty array for empty input", () => {
    expect(extractCompanies([])).toEqual([]);
  });

  it("flattens and deduplicates across entries", () => {
    const data = [
      makeProblem({ companies: ["Google", "Meta"] }),
      makeProblem({ companies: ["Google", "Amazon"] }),
    ];
    expect(extractCompanies(data)).toEqual(["Amazon", "Google", "Meta"]);
  });
});

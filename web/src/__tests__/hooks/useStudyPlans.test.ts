import { describe, it, expect, vi, beforeEach } from "vitest";
import { renderHook, waitFor } from "@testing-library/react";
import { useStudyPlans } from "../../hooks/useStudyPlans";
import type { ProblemEntry } from "../../lib/types";

const mockProblems: ProblemEntry[] = [
  {
    name: "Two Sum",
    pattern: "01_arrays_and_strings",
    subPattern: null,
    difficulty: "Easy",
    companies: ["Google"],
    path: "src/01_arrays_and_strings/two_sum",
    sourceUrl: "https://leetcode.com/problems/two-sum",
  },
];

const validPlanJson = [
  {
    id: "test-plan",
    name: "Test Plan",
    description: "A test plan",
    author: "test",
    tags: ["test"],
    sections: [
      { order: 1, focus: "Basics", problemNames: ["Two Sum"] },
    ],
  },
];

beforeEach(() => {
  vi.restoreAllMocks();
});

describe("useStudyPlans", () => {
  it("returns plans after successful fetch with valid data", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: true,
        json: () => Promise.resolve(validPlanJson),
      }),
    );

    const { result } = renderHook(() => useStudyPlans(mockProblems));
    expect(result.current.loading).toBe(true);

    await waitFor(() => expect(result.current.loading).toBe(false));
    expect(result.current.plans).toHaveLength(1);
    expect(result.current.plans[0].id).toBe("test-plan");
    expect(result.current.plans[0].name).toBe("Test Plan");
    expect(result.current.plans[0].sections[0].problemNames).toEqual(["Two Sum"]);
    expect(result.current.error).toBeNull();
  });

  it("sets error on fetch failure (non-ok response)", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: false,
        status: 404,
        statusText: "Not Found",
      }),
    );

    const { result } = renderHook(() => useStudyPlans(mockProblems));
    await waitFor(() => expect(result.current.loading).toBe(false));
    expect(result.current.error).toBe(
      "Failed to load study plans: 404 Not Found",
    );
    expect(result.current.plans).toEqual([]);
  });

  it("sets error on network failure", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockRejectedValue(new Error("Network error")),
    );

    const { result } = renderHook(() => useStudyPlans(mockProblems));
    await waitFor(() => expect(result.current.loading).toBe(false));
    expect(result.current.error).toBe("Network error");
    expect(result.current.plans).toEqual([]);
  });

  it("logs warnings when plan has invalid problem references", async () => {
    const warnSpy = vi.spyOn(console, "warn").mockImplementation(() => {});

    const planWithBadRef = [
      {
        id: "warn-plan",
        name: "Warn Plan",
        description: "Has bad refs",
        author: "test",
        tags: ["test"],
        sections: [
          {
            order: 1,
            focus: "Mixed",
            problemNames: ["Two Sum", "Nonexistent Problem"],
          },
        ],
      },
    ];

    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: true,
        json: () => Promise.resolve(planWithBadRef),
      }),
    );

    const { result } = renderHook(() => useStudyPlans(mockProblems));
    await waitFor(() => expect(result.current.loading).toBe(false));

    expect(warnSpy).toHaveBeenCalledWith(
      expect.stringContaining('unknown problem "Nonexistent Problem"'),
    );
    // The valid problem should still be present
    expect(result.current.plans[0].sections[0].problemNames).toEqual([
      "Two Sum",
    ]);
  });

  it("returns empty plans when JSON is not an array", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: true,
        json: () => Promise.resolve({ not: "an array" }),
      }),
    );

    const { result } = renderHook(() => useStudyPlans(mockProblems));
    await waitFor(() => expect(result.current.loading).toBe(false));
    expect(result.current.plans).toEqual([]);
    expect(result.current.error).toBeNull();
  });
});

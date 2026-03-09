import { describe, it, expect, vi, beforeEach } from "vitest";
import { renderHook, waitFor } from "@testing-library/react";
import { useProblems } from "./useProblems";
import type { ProblemEntry } from "../lib/types";

const mockProblems: ProblemEntry[] = [
  {
    name: "two_sum",
    pattern: "01_arrays_and_strings",
    subPattern: null,
    difficulty: "Easy",
    companies: ["Google"],
    path: "src/01_arrays_and_strings/two_sum",
    sourceUrl: "https://leetcode.com/problems/two-sum",
  },
];

beforeEach(() => {
  vi.restoreAllMocks();
});

describe("useProblems", () => {
  it("returns problems after successful fetch", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: true,
        json: () => Promise.resolve(mockProblems),
      })
    );

    const { result } = renderHook(() => useProblems());
    expect(result.current.loading).toBe(true);

    await waitFor(() => expect(result.current.loading).toBe(false));
    expect(result.current.problems).toEqual(mockProblems);
    expect(result.current.error).toBeNull();
  });

  it("sets error on fetch failure (non-ok response)", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: false,
        status: 404,
        statusText: "Not Found",
      })
    );

    const { result } = renderHook(() => useProblems());
    await waitFor(() => expect(result.current.loading).toBe(false));
    expect(result.current.error).toBe("Failed to load problems: 404 Not Found");
    expect(result.current.problems).toEqual([]);
  });

  it("sets error on network failure", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockRejectedValue(new Error("Network error"))
    );

    const { result } = renderHook(() => useProblems());
    await waitFor(() => expect(result.current.loading).toBe(false));
    expect(result.current.error).toBe("Network error");
    expect(result.current.problems).toEqual([]);
  });

  it("sets error on invalid JSON", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: true,
        json: () => Promise.reject(new SyntaxError("Unexpected token")),
      })
    );

    const { result } = renderHook(() => useProblems());
    await waitFor(() => expect(result.current.loading).toBe(false));
    expect(result.current.error).toBe("Unexpected token");
    expect(result.current.problems).toEqual([]);
  });
});

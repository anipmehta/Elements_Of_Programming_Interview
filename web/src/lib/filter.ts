import type { ProblemEntry, FilterState } from "./types";

/**
 * Returns entries matching ALL active filters (AND logic).
 * A null filter field means "no constraint" for that dimension.
 */
export function applyFilters(
  problems: ProblemEntry[],
  filters: FilterState
): ProblemEntry[] {
  return problems.filter((entry) => {
    if (filters.pattern !== null && entry.pattern !== filters.pattern) {
      return false;
    }
    if (filters.difficulty !== null && entry.difficulty !== filters.difficulty) {
      return false;
    }
    if (filters.company !== null && !entry.companies.includes(filters.company)) {
      return false;
    }
    return true;
  });
}

/** Returns sorted unique pattern values from the problems array. */
export function extractPatterns(problems: ProblemEntry[]): string[] {
  const patterns = new Set(problems.map((p) => p.pattern));
  return [...patterns].sort();
}

/** Returns sorted unique company names flattened from all companies arrays. */
export function extractCompanies(problems: ProblemEntry[]): string[] {
  const companies = new Set(problems.flatMap((p) => p.companies));
  return [...companies].sort();
}

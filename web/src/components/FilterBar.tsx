import type { FilterState } from "../lib/types";

interface FilterBarProps {
  patterns: string[];
  difficulties: string[];
  companies: string[];
  filters: FilterState;
  onFilterChange: (filters: FilterState) => void;
}

/**
 * Strips the numeric prefix and formats a raw pattern string for display.
 * e.g. "01_arrays_and_strings" → "Arrays And Strings"
 */
function formatPattern(raw: string): string {
  const stripped = raw.replace(/^\d+_/, "");
  return stripped
    .split("_")
    .map((w) => w.charAt(0).toUpperCase() + w.slice(1))
    .join(" ");
}

export default function FilterBar({
  patterns,
  difficulties,
  companies,
  filters,
  onFilterChange,
}: FilterBarProps) {
  return (
    <div className="filter-bar">
      <div>
        <label htmlFor="pattern-filter">Pattern</label>
        <select
          id="pattern-filter"
          value={filters.pattern ?? ""}
          onChange={(e) =>
            onFilterChange({
              ...filters,
              pattern: e.target.value || null,
            })
          }
        >
          <option value="">All</option>
          {patterns.map((p) => (
            <option key={p} value={p}>
              {formatPattern(p)}
            </option>
          ))}
        </select>
      </div>

      <div>
        <label htmlFor="difficulty-filter">Difficulty</label>
        <select
          id="difficulty-filter"
          value={filters.difficulty ?? ""}
          onChange={(e) =>
            onFilterChange({
              ...filters,
              difficulty: e.target.value || null,
            })
          }
        >
          <option value="">All</option>
          {difficulties.map((d) => (
            <option key={d} value={d}>
              {d}
            </option>
          ))}
        </select>
      </div>

      <div>
        <label htmlFor="company-filter">Company</label>
        <select
          id="company-filter"
          value={filters.company ?? ""}
          onChange={(e) =>
            onFilterChange({
              ...filters,
              company: e.target.value || null,
            })
          }
        >
          <option value="">All</option>
          {companies.map((c) => (
            <option key={c} value={c}>
              {c}
            </option>
          ))}
        </select>
      </div>
    </div>
  );
}

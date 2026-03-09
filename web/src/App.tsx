import { useState, useMemo } from "react";
import "./index.css";
import { useProblems } from "./hooks/useProblems";
import { applyFilters, extractPatterns, extractCompanies } from "./lib/filter";
import type { FilterState } from "./lib/types";
import FilterBar from "./components/FilterBar";
import StatusBar from "./components/StatusBar";
import ProblemTable from "./components/ProblemTable";

const DIFFICULTIES = ["Easy", "Medium", "Hard"];

export default function App() {
  const { problems, loading, error } = useProblems();
  const [filters, setFilters] = useState<FilterState>({
    pattern: null,
    difficulty: null,
    company: null,
  });

  const filteredProblems = useMemo(
    () => applyFilters(problems, filters),
    [problems, filters]
  );

  const patterns = useMemo(() => extractPatterns(problems), [problems]);
  const companies = useMemo(() => extractCompanies(problems), [problems]);

  if (loading) {
    return <p>Loading problems...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <div>
      <h1>Study Tracker</h1>
      <FilterBar
        patterns={patterns}
        difficulties={DIFFICULTIES}
        companies={companies}
        filters={filters}
        onFilterChange={setFilters}
      />
      <StatusBar
        visibleCount={filteredProblems.length}
        totalCount={problems.length}
      />
      <ProblemTable problems={filteredProblems} />
    </div>
  );
}

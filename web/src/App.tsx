import { useState, useMemo, useCallback } from "react";
import "./index.css";
import { useProblems } from "./hooks/useProblems";
import { applyFilters, extractPatterns, extractCompanies } from "./lib/filter";
import { createProgressStore } from "./lib/progressStore";
import { computeOverall, computeByDifficulty, computeByPattern } from "./lib/stats";
import type { FilterState } from "./lib/types";
import FilterBar from "./components/FilterBar";
import StatusBar from "./components/StatusBar";
import StatsPanel from "./components/StatsPanel";
import ProblemTable from "./components/ProblemTable";
import Footer from "./components/Footer";

const DIFFICULTIES = ["Easy", "Medium", "Hard"];
const store = createProgressStore();

export default function App() {
  const { problems, loading, error } = useProblems();
  const [filters, setFilters] = useState<FilterState>({
    pattern: null,
    difficulty: null,
    company: null,
  });
  const [completed, setCompleted] = useState<Set<string>>(() => store.getCompleted());

  const handleToggle = useCallback((name: string) => {
    setCompleted((prev) => {
      if (prev.has(name)) {
        store.markIncomplete(name);
      } else {
        store.markCompleted(name);
      }
      return store.getCompleted();
    });
  }, []);

  const filteredProblems = useMemo(
    () => applyFilters(problems, filters),
    [problems, filters]
  );

  const patterns = useMemo(() => extractPatterns(problems), [problems]);
  const companies = useMemo(() => extractCompanies(problems), [problems]);

  const overall = useMemo(() => computeOverall(problems, completed), [problems, completed]);
  const byDifficulty = useMemo(() => computeByDifficulty(problems, completed), [problems, completed]);
  const byPattern = useMemo(() => computeByPattern(problems, completed), [problems, completed]);

  if (loading) {
    return <p>Loading problems...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <div>
      <h1>AlgoForge</h1>
      {store.isMemoryFallback && (
        <p className="warning">localStorage unavailable — progress won't persist across reloads.</p>
      )}
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
      <StatsPanel overall={overall} byDifficulty={byDifficulty} byPattern={byPattern} />
      <ProblemTable problems={filteredProblems} completed={completed} onToggle={handleToggle} />
      <Footer />
    </div>
  );
}

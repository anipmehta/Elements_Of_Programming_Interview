import { useState, useMemo, useCallback } from "react";
import "./index.css";
import { useProblems } from "./hooks/useProblems";
import { useStudyPlans } from "./hooks/useStudyPlans";
import { applyFilters, extractPatterns, extractCompanies } from "./lib/filter";
import { createProgressStore } from "./lib/progressStore";
import { createPlanStore } from "./lib/planStore";
import { computeOverall, computeByDifficulty, computeByPattern } from "./lib/stats";
import type { FilterState } from "./lib/types";
import FilterBar from "./components/FilterBar";
import StatusBar from "./components/StatusBar";
import StatsPanel from "./components/StatsPanel";
import ProblemTable from "./components/ProblemTable";
import PlanSelector from "./components/PlanSelector";
import PlanView from "./components/PlanView";
import Footer from "./components/Footer";

const DIFFICULTIES = ["Easy", "Medium", "Hard"];
const store = createProgressStore();
const planStore = createPlanStore();

export default function App() {
  const { problems, loading, error } = useProblems();
  const [view, setView] = useState<"problems" | "plans">("problems");
  const [filters, setFilters] = useState<FilterState>({
    pattern: null,
    difficulty: null,
    company: null,
  });
  const [completed, setCompleted] = useState<Set<string>>(() => store.getCompleted());
  const [activePlanId, setActivePlanId] = useState<string | null>(
    () => planStore.getActivePlanId()
  );

  const { plans } = useStudyPlans(problems);

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

  const handleSelectPlan = useCallback((id: string) => {
    planStore.setActivePlan(id);
    setActivePlanId(id);
  }, []);

  const handleDeactivatePlan = useCallback(() => {
    planStore.clearActivePlan();
    setActivePlanId(null);
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

  const activePlan = useMemo(
    () => plans.find((p) => p.id === activePlanId) ?? null,
    [plans, activePlanId]
  );

  if (loading) {
    return <p>Loading problems...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <div>
      <h1>AlgoForge</h1>
      {(store.isMemoryFallback || planStore.isMemoryFallback) && (
        <p className="warning">localStorage unavailable — progress won't persist across reloads.</p>
      )}
      <nav className="view-nav">
        <button
          className={`view-nav-button${view === "problems" ? " view-nav-active" : ""}`}
          onClick={() => setView("problems")}
        >
          Problems
        </button>
        <button
          className={`view-nav-button${view === "plans" ? " view-nav-active" : ""}`}
          onClick={() => setView("plans")}
        >
          Study Plans
        </button>
      </nav>
      {view === "problems" && (
        <>
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
        </>
      )}
      {view === "plans" && (
        <>
          <PlanSelector
            plans={plans}
            activePlanId={activePlanId}
            onSelectPlan={handleSelectPlan}
            onDeactivatePlan={handleDeactivatePlan}
          />
          {activePlan && (
            <PlanView
              plan={activePlan}
              problems={problems}
              completed={completed}
              onToggle={handleToggle}
            />
          )}
        </>
      )}
      <Footer />
    </div>
  );
}

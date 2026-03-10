import { useState, useEffect } from "react";
import type { ProblemEntry } from "../lib/types";
import type { StudyPlan } from "../lib/studyPlanTypes";
import { loadStudyPlans } from "../lib/studyPlanLoader";

export interface UseStudyPlansResult {
  plans: StudyPlan[];
  loading: boolean;
  error: string | null;
}

export function useStudyPlans(problems: ProblemEntry[]): UseStudyPlansResult {
  const [plans, setPlans] = useState<StudyPlan[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    let cancelled = false;

    async function fetchPlans() {
      try {
        const response = await fetch(
          `${import.meta.env.BASE_URL}study-plans.json`,
        );
        if (!response.ok) {
          throw new Error(
            `Failed to load study plans: ${response.status} ${response.statusText}`,
          );
        }
        const data: unknown = await response.json();
        const validNames = new Set(problems.map((p) => p.name));
        const result = loadStudyPlans(data, validNames);

        for (const warning of result.warnings) {
          console.warn(warning);
        }

        if (!cancelled) {
          setPlans(result.plans);
        }
      } catch (err) {
        if (!cancelled) {
          setError(
            err instanceof Error ? err.message : "Failed to load study plans",
          );
        }
      } finally {
        if (!cancelled) {
          setLoading(false);
        }
      }
    }

    fetchPlans();

    return () => {
      cancelled = true;
    };
  }, [problems]);

  return { plans, loading, error };
}

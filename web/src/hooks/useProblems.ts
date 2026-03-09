import { useState, useEffect } from "react";
import type { ProblemEntry } from "../lib/types";

interface UseProblemsResult {
  problems: ProblemEntry[];
  loading: boolean;
  error: string | null;
}

export function useProblems(): UseProblemsResult {
  const [problems, setProblems] = useState<ProblemEntry[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    let cancelled = false;

    async function fetchProblems() {
      try {
        const response = await fetch(`${import.meta.env.BASE_URL}problems.json`);
        if (!response.ok) {
          throw new Error(`Failed to load problems: ${response.status} ${response.statusText}`);
        }
        const data: ProblemEntry[] = await response.json();
        if (!cancelled) {
          setProblems(data);
        }
      } catch (err) {
        if (!cancelled) {
          setError(err instanceof Error ? err.message : "Failed to load problems");
        }
      } finally {
        if (!cancelled) {
          setLoading(false);
        }
      }
    }

    fetchProblems();

    return () => {
      cancelled = true;
    };
  }, []);

  return { problems, loading, error };
}

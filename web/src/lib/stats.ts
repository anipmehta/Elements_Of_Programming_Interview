import type { ProblemEntry } from "./types";

export interface OverallStats {
  total: number;
  completed: number;
  percentage: number;
}

export interface GroupStats {
  label: string;
  total: number;
  completed: number;
  percentage: number;
}

export function computeOverall(
  problems: ProblemEntry[],
  completed: Set<string>
): OverallStats {
  const total = problems.length;
  const done = problems.filter((p) => completed.has(p.name)).length;
  return {
    total,
    completed: done,
    percentage: total === 0 ? 0 : Math.round((done / total) * 100),
  };
}

export function computeByDifficulty(
  problems: ProblemEntry[],
  completed: Set<string>
): GroupStats[] {
  const difficulties: Array<"Easy" | "Medium" | "Hard"> = ["Easy", "Medium", "Hard"];
  return difficulties.map((d) => {
    const group = problems.filter((p) => p.difficulty === d);
    const done = group.filter((p) => completed.has(p.name)).length;
    return {
      label: d,
      total: group.length,
      completed: done,
      percentage: group.length === 0 ? 0 : Math.round((done / group.length) * 100),
    };
  });
}

export function computeByPattern(
  problems: ProblemEntry[],
  completed: Set<string>
): GroupStats[] {
  const patternMap = new Map<string, ProblemEntry[]>();
  for (const p of problems) {
    const list = patternMap.get(p.pattern) ?? [];
    list.push(p);
    patternMap.set(p.pattern, list);
  }

  return [...patternMap.entries()]
    .sort(([a], [b]) => a.localeCompare(b))
    .map(([pattern, group]) => {
      const done = group.filter((p) => completed.has(p.name)).length;
      return {
        label: pattern.replace(/^\d+_/, "").replace(/_/g, " "),
        total: group.length,
        completed: done,
        percentage: group.length === 0 ? 0 : Math.round((done / group.length) * 100),
      };
    });
}

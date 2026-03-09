export interface ProblemEntry {
  name: string;
  pattern: string;
  subPattern: string | null;
  difficulty: "Easy" | "Medium" | "Hard";
  companies: string[];
  path: string;
  sourceUrl: string;
}

export interface FilterState {
  pattern: string | null;    // null = show all
  difficulty: string | null; // null = show all
  company: string | null;    // null = show all
}

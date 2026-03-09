/**
 * Strips the numeric prefix and formats a raw pattern/sub-pattern string for display.
 * e.g. "01_arrays_and_strings" → "Arrays & Strings"
 * e.g. "sliding_window" → "Sliding Window"
 * e.g. "dfs_bfs" → "DFS BFS"
 */

const UPPERCASE_TOKENS = new Set(["dfs", "bfs", "ii", "lru", "oa"]);
const AMPERSAND_PAIRS: Record<string, string> = {
  "arrays and strings": "Arrays & Strings",
  "stacks and queues": "Stacks & Queues",
  "heaps and priority queues": "Heaps & Priority Queues",
  "recursion and backtracking": "Recursion & Backtracking",
};

export function formatPattern(raw: string): string {
  const stripped = raw.replace(/^\d+_/, "");
  const spaced = stripped.replace(/_/g, " ");

  const ampersandMatch = AMPERSAND_PAIRS[spaced.toLowerCase()];
  if (ampersandMatch) return ampersandMatch;

  return spaced
    .split(" ")
    .map((w) =>
      UPPERCASE_TOKENS.has(w.toLowerCase())
        ? w.toUpperCase()
        : w.charAt(0).toUpperCase() + w.slice(1)
    )
    .join(" ");
}

/**
 * Builds a GitHub URL to the PATTERN.md for a given pattern folder.
 * e.g. "01_arrays_and_strings" → "https://github.com/anipmehta/AlgoForge/blob/master/src/01_arrays_and_strings/PATTERN.md"
 */
export function patternDocUrl(pattern: string): string {
  return `https://github.com/anipmehta/AlgoForge/blob/master/src/${pattern}/PATTERN.md`;
}

import type { ProblemEntry } from "../lib/types";

interface ProblemTableProps {
  problems: ProblemEntry[];
  completed: Set<string>;
  onToggle: (name: string) => void;
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

export default function ProblemTable({ problems, completed, onToggle }: ProblemTableProps) {
  return (
    <div className="table-container">
      <table>
        <thead>
          <tr>
            <th>✓</th>
            <th>Name</th>
            <th>Pattern</th>
            <th>Sub-Pattern</th>
            <th>Difficulty</th>
            <th>Companies</th>
          </tr>
        </thead>
        <tbody>
          {problems.map((p) => (
            <tr key={p.name} className={completed.has(p.name) ? "completed-row" : ""}>
              <td>
                <input
                  type="checkbox"
                  checked={completed.has(p.name)}
                  onChange={() => onToggle(p.name)}
                  aria-label={`Mark ${p.name} as ${completed.has(p.name) ? "incomplete" : "complete"}`}
                />
              </td>
              <td>
                {p.sourceUrl !== "TBD" ? (
                  <a
                    href={p.sourceUrl}
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    {p.name}
                  </a>
                ) : (
                  p.name
                )}
                {" "}
                <a
                  href={`https://github.com/anipmehta/AlgoForge/tree/master/${p.path}`}
                  target="_blank"
                  rel="noopener noreferrer"
                  className="solution-link"
                  title="View solution on GitHub"
                >
                  📂
                </a>
              </td>
              <td>{formatPattern(p.pattern)}</td>
              <td>{p.subPattern ?? "—"}</td>
              <td>{p.difficulty}</td>
              <td>{p.companies.length > 0 ? p.companies.join(", ") : "—"}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

import type { ProblemEntry } from "../lib/types";

interface ProblemTableProps {
  problems: ProblemEntry[];
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

export default function ProblemTable({ problems }: ProblemTableProps) {
  return (
    <div className="table-container">
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Pattern</th>
            <th>Sub-Pattern</th>
            <th>Difficulty</th>
            <th>Companies</th>
          </tr>
        </thead>
        <tbody>
          {problems.map((p) => (
            <tr key={p.name}>
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
                  href={`https://github.com/anipmehta/Elements_Of_Programming_Interview/tree/master/${p.path}`}
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

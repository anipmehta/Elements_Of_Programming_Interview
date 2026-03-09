import type { ProblemEntry } from "../lib/types";
import { formatPattern, patternDocUrl } from "../lib/format";

interface ProblemTableProps {
  problems: ProblemEntry[];
  completed: Set<string>;
  onToggle: (name: string) => void;
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
              <td>
                <a
                  href={patternDocUrl(p.pattern)}
                  target="_blank"
                  rel="noopener noreferrer"
                  title={`Learn about ${formatPattern(p.pattern)}`}
                >
                  {formatPattern(p.pattern)}
                </a>
              </td>
              <td>{p.subPattern ? formatPattern(p.subPattern) : "—"}</td>
              <td>{p.difficulty}</td>
              <td>{p.companies.length > 0 ? p.companies.join(", ") : "—"}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

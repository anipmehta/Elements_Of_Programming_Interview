import { useState } from "react";
import type { OverallStats, GroupStats } from "../lib/stats";

interface StatsPanelProps {
  overall: OverallStats;
  byDifficulty: GroupStats[];
  byPattern: GroupStats[];
}

function ProgressBar({ percentage }: { percentage: number }) {
  return (
    <div className="progress-bar" role="progressbar" aria-valuenow={percentage} aria-valuemin={0} aria-valuemax={100}>
      <div className="progress-fill" style={{ width: `${percentage}%` }} />
    </div>
  );
}

export default function StatsPanel({ overall, byDifficulty, byPattern }: StatsPanelProps) {
  const [expanded, setExpanded] = useState(false);

  return (
    <div className="stats-panel">
      <div className="stats-overall">
        <span className="stats-label">
          Progress: {overall.completed} / {overall.total} ({overall.percentage}%)
        </span>
        <ProgressBar percentage={overall.percentage} />
      </div>

      <div className="stats-difficulty">
        {byDifficulty.map((d) => (
          <span key={d.label} className={`stats-badge stats-${d.label.toLowerCase()}`}>
            {d.label}: {d.completed}/{d.total}
          </span>
        ))}
      </div>

      <button
        className="stats-toggle"
        onClick={() => setExpanded(!expanded)}
        aria-expanded={expanded}
      >
        {expanded ? "Hide" : "Show"} pattern breakdown
      </button>

      {expanded && (
        <div className="stats-patterns">
          {byPattern.map((p) => (
            <div key={p.label} className="stats-pattern-row">
              <span className="stats-pattern-label">{p.label}</span>
              <span className="stats-pattern-count">{p.completed}/{p.total}</span>
              <ProgressBar percentage={p.percentage} />
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

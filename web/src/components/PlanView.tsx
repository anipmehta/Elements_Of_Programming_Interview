import type { StudyPlan } from "../lib/studyPlanTypes";
import type { ProblemEntry } from "../lib/types";
import { computePlanProgress, isSectionComplete } from "../lib/studyPlanLoader";
import { formatPattern } from "../lib/format";

interface PlanViewProps {
  plan: StudyPlan;
  problems: ProblemEntry[];
  completed: Set<string>;
  onToggle: (name: string) => void;
}

export default function PlanView({
  plan,
  problems,
  completed,
  onToggle,
}: PlanViewProps) {
  const progress = computePlanProgress(plan, completed);

  const problemMap = new Map<string, ProblemEntry>();
  for (const p of problems) {
    problemMap.set(p.name, p);
  }

  return (
    <section className="plan-view" aria-label={`Study plan: ${plan.name}`}>
      <div className="plan-progress" role="region" aria-label="Plan progress">
        <div className="plan-progress-bar-track">
          <div
            className="plan-progress-bar-fill"
            style={{ width: `${progress.percentage}%` }}
          />
        </div>
        <p className="plan-progress-text">
          {progress.completed} / {progress.total} completed ({progress.percentage}%)
        </p>
      </div>

      <ol className="plan-sections">
        {plan.sections.map((section) => {
          const sectionDone = isSectionComplete(section, completed);
          return (
            <li key={section.order} className="plan-section">
              <div className="plan-section-header">
                <span className="plan-section-order">Section {section.order}</span>
                {section.focus && (
                  <span className="plan-section-focus"> — {section.focus}</span>
                )}
                {sectionDone && (
                  <span className="plan-section-complete" aria-label="Section complete">
                    {" "}✓
                  </span>
                )}
              </div>

              <ul className="plan-section-problems">
                {section.problemNames.map((name) => {
                  const entry = problemMap.get(name);
                  const isCompleted = completed.has(name);
                  return (
                    <li key={name} className="plan-problem">
                      <label className="plan-problem-label">
                        <input
                          type="checkbox"
                          checked={isCompleted}
                          onChange={() => onToggle(name)}
                          aria-label={`Mark ${name} as ${isCompleted ? "incomplete" : "complete"}`}
                        />
                        <span className="plan-problem-name">{name}</span>
                      </label>
                      {entry && (
                        <>
                          <span className={`plan-problem-difficulty plan-problem-difficulty-${entry.difficulty.toLowerCase()}`}>
                            {entry.difficulty}
                          </span>
                          <span className="plan-problem-pattern">
                            {formatPattern(entry.pattern)}
                          </span>
                          {entry.sourceUrl && entry.sourceUrl !== "TBD" && (
                            <a
                              href={entry.sourceUrl}
                              target="_blank"
                              rel="noopener noreferrer"
                              className="plan-problem-link"
                            >
                              Source
                            </a>
                          )}
                        </>
                      )}
                    </li>
                  );
                })}
              </ul>
            </li>
          );
        })}
      </ol>
    </section>
  );
}

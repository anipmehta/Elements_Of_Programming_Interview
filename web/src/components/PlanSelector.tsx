import type { StudyPlan } from "../lib/studyPlanTypes";

interface PlanSelectorProps {
  plans: StudyPlan[];
  activePlanId: string | null;
  onSelectPlan: (id: string) => void;
  onDeactivatePlan: () => void;
}

export default function PlanSelector({
  plans,
  activePlanId,
  onSelectPlan,
  onDeactivatePlan,
}: PlanSelectorProps) {
  return (
    <section className="plan-selector" aria-label="Study Plans">
      <ul className="plan-list">
        {plans.map((plan) => {
          const isActive = plan.id === activePlanId;
          return (
            <li
              key={plan.id}
              className={`plan-card${isActive ? " plan-card-active" : ""}`}
            >
              <h3 className="plan-card-name">{plan.name}</h3>
              <p className="plan-card-description">{plan.description}</p>
              <p className="plan-card-author">By {plan.author}</p>
              {plan.tags.length > 0 && (
                <ul className="plan-card-tags" aria-label={`Tags for ${plan.name}`}>
                  {plan.tags.map((tag) => (
                    <li key={tag} className="plan-card-tag">
                      {tag}
                    </li>
                  ))}
                </ul>
              )}
              {isActive ? (
                <button
                  className="plan-card-button plan-card-deactivate"
                  onClick={onDeactivatePlan}
                  aria-label={`Deactivate ${plan.name}`}
                >
                  Deactivate
                </button>
              ) : (
                <button
                  className="plan-card-button plan-card-select"
                  onClick={() => onSelectPlan(plan.id)}
                  aria-label={`Select ${plan.name}`}
                >
                  Select
                </button>
              )}
            </li>
          );
        })}
      </ul>
    </section>
  );
}

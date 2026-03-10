import type { PlanSection, StudyPlan, RawStudyPlan, RawPlanSection } from "./studyPlanTypes";

export interface LoadResult {
  plans: StudyPlan[];
  warnings: string[];
}

function isStringArray(value: unknown): value is string[] {
  return Array.isArray(value) && value.every((v) => typeof v === "string");
}

function validateSection(
  raw: RawPlanSection,
  planId: string,
  validNames: Set<string>,
  warnings: string[],
): PlanSection | null {
  if (typeof raw.order !== "number" || raw.order < 1) {
    warnings.push(
      `[study-plans] Plan "${planId}" section with order ${String(raw.order)} excluded (must be >= 1)`,
    );
    return null;
  }

  if (!isStringArray(raw.problemNames) || raw.problemNames.length === 0) {
    return null;
  }

  const filtered: string[] = [];
  for (const name of raw.problemNames) {
    if (validNames.has(name)) {
      filtered.push(name);
    } else {
      warnings.push(
        `[study-plans] Plan "${planId}" section ${raw.order}: unknown problem "${name}" — removed`,
      );
    }
  }

  const section: PlanSection = {
    order: raw.order,
    problemNames: filtered,
  };

  if (typeof raw.focus === "string" && raw.focus.length > 0) {
    section.focus = raw.focus;
  }

  return section;
}

function validatePlan(
  raw: RawStudyPlan,
  validNames: Set<string>,
  warnings: string[],
): StudyPlan | null {
  if (
    typeof raw.id !== "string" ||
    typeof raw.name !== "string" ||
    typeof raw.description !== "string" ||
    typeof raw.author !== "string" ||
    !isStringArray(raw.tags) ||
    !Array.isArray(raw.sections)
  ) {
    return null;
  }

  const sections = (raw.sections as RawPlanSection[])
    .map((s) => validateSection(s, raw.id as string, validNames, warnings))
    .filter((s): s is PlanSection => s !== null);

  if (sections.length === 0) {
    warnings.push(
      `[study-plans] Plan "${raw.id}" has no valid sections — excluded`,
    );
    return null;
  }

  return {
    id: raw.id,
    name: raw.name,
    description: raw.description,
    author: raw.author,
    tags: raw.tags,
    sections: sections.sort((a, b) => a.order - b.order),
  };
}

export function loadStudyPlans(
  raw: unknown,
  validProblemNames: Set<string>,
): LoadResult {
  const warnings: string[] = [];

  if (!Array.isArray(raw)) {
    return { plans: [], warnings };
  }

  const seenIds = new Set<string>();
  const plans: StudyPlan[] = [];

  for (const item of raw as RawStudyPlan[]) {
    if (typeof item.id === "string" && seenIds.has(item.id)) {
      warnings.push(
        `[study-plans] Duplicate plan ID "${item.id}" — skipping subsequent occurrence`,
      );
      continue;
    }

    const plan = validatePlan(item, validProblemNames, warnings);
    if (plan) {
      seenIds.add(plan.id);
      plans.push(plan);
    }
  }

  return { plans, warnings };
}

export function computePlanProgress(
  plan: StudyPlan,
  completed: Set<string>,
): { completed: number; total: number; percentage: number } {
  const allNames = plan.sections.flatMap((s) => s.problemNames);
  const done = allNames.filter((name) => completed.has(name)).length;
  const total = allNames.length;
  return {
    completed: done,
    total,
    percentage: total === 0 ? 0 : Math.round((done / total) * 100),
  };
}

export function isSectionComplete(
  section: PlanSection,
  completed: Set<string>,
): boolean {
  return (
    section.problemNames.length > 0 &&
    section.problemNames.every((name) => completed.has(name))
  );
}

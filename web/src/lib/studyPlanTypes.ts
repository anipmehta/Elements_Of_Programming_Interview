/** A single section within a study plan */
export interface PlanSection {
  order: number;
  focus?: string;
  problemNames: string[];
}

/** A complete study plan as stored in study-plans.json */
export interface StudyPlan {
  id: string;
  name: string;
  description: string;
  author: string;
  tags: string[];
  sections: PlanSection[];
}

/** Raw JSON shape before validation (used by loader) */
export interface RawPlanSection {
  order?: unknown;
  focus?: unknown;
  problemNames?: unknown;
}

export interface RawStudyPlan {
  id?: unknown;
  name?: unknown;
  description?: unknown;
  author?: unknown;
  tags?: unknown;
  sections?: unknown;
}

import { describe, it, expect } from "vitest";
import fc from "fast-check";
import { computePlanProgress, isSectionComplete } from "../../lib/studyPlanLoader";
import type { PlanSection, StudyPlan } from "../../lib/studyPlanTypes";

const NAMES = ["A", "B", "C", "D", "E", "F", "G", "H"];

const sectionArb: fc.Arbitrary<PlanSection> = fc.record({
  order: fc.integer({ min: 1, max: 50 }),
  problemNames: fc.subarray(NAMES, { minLength: 0, maxLength: 5 }),
});

const planArb: fc.Arbitrary<StudyPlan> = fc.record({
  id: fc.constant("test"),
  name: fc.constant("Test"),
  description: fc.constant("d"),
  author: fc.constant("a"),
  tags: fc.constant([]),
  sections: fc.array(sectionArb, { minLength: 0, maxLength: 5 }),
});

describe("plan progress", () => {
  // Feature: study-plans, Property 6: Section Completion Correctness
  describe("Property 6: Section Completion Correctness", () => {
    it("returns true iff every problemName is completed and section is non-empty", () => {
      fc.assert(
        fc.property(
          sectionArb,
          fc.subarray(NAMES),
          (section, completedArr) => {
            const completed = new Set(completedArr);
            const result = isSectionComplete(section, completed);
            const expected =
              section.problemNames.length > 0 &&
              section.problemNames.every((n) => completed.has(n));
            expect(result).toBe(expected);
          },
        ),
        { numRuns: 100 },
      );
    });
  });

  // Feature: study-plans, Property 7: Plan Progress Computation
  describe("Property 7: Plan Progress Computation", () => {
    it("returns correct completed count, total, and percentage", () => {
      fc.assert(
        fc.property(
          planArb,
          fc.subarray(NAMES),
          (plan, completedArr) => {
            const completed = new Set(completedArr);
            const result = computePlanProgress(plan, completed);
            const allNames = plan.sections.flatMap((s) => s.problemNames);
            const expectedDone = allNames.filter((n) => completed.has(n)).length;
            const expectedTotal = allNames.length;
            const expectedPct =
              expectedTotal === 0 ? 0 : Math.round((expectedDone / expectedTotal) * 100);

            expect(result.completed).toBe(expectedDone);
            expect(result.total).toBe(expectedTotal);
            expect(result.percentage).toBe(expectedPct);
          },
        ),
        { numRuns: 100 },
      );
    });
  });
});

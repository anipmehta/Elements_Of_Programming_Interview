import { describe, it, expect } from "vitest";
import fc from "fast-check";
import { loadStudyPlans } from "../../lib/studyPlanLoader";

// Arbitraries for generating valid study plan data
const planSectionArb = (validNames: string[]) =>
  fc.record({
    order: fc.integer({ min: 1, max: 100 }),
    focus: fc.option(fc.string({ minLength: 1, maxLength: 30 }), { nil: undefined }),
    problemNames: fc.subarray(validNames, { minLength: 1 }),
  });

const studyPlanArb = (validNames: string[]) =>
  fc.record({
    id: fc.string({ minLength: 1, maxLength: 20 }).filter((s) => s.trim().length > 0),
    name: fc.string({ minLength: 1, maxLength: 50 }),
    description: fc.string({ minLength: 1, maxLength: 100 }),
    author: fc.string({ minLength: 1, maxLength: 30 }),
    tags: fc.array(fc.string({ minLength: 1, maxLength: 15 }), { minLength: 0, maxLength: 5 }),
    sections: fc.array(planSectionArb(validNames), { minLength: 1, maxLength: 5 }),
  });

const SAMPLE_NAMES = ["Two Sum", "3Sum", "Group Anagrams", "Valid Palindrome", "Best Meeting Point"];

describe("studyPlanLoader", () => {
  // Feature: study-plans, Property 1: JSON Serialization Round-Trip
  describe("Property 1: JSON Serialization Round-Trip", () => {
    it("round-trips through JSON without data loss", () => {
      fc.assert(
        fc.property(studyPlanArb(SAMPLE_NAMES), (plan) => {
          const roundTripped = JSON.parse(JSON.stringify(plan));
          expect(roundTripped).toEqual(plan);
        }),
        { numRuns: 100 },
      );
    });
  });

  // Feature: study-plans, Property 2: Schema Validation Accepts Valid and Rejects Invalid
  describe("Property 2: Schema Validation", () => {
    it("accepts valid plans with all required fields", () => {
      fc.assert(
        fc.property(studyPlanArb(SAMPLE_NAMES), (plan) => {
          const validNames = new Set(SAMPLE_NAMES);
          const result = loadStudyPlans([plan], validNames);
          expect(result.plans.length).toBe(1);
          expect(result.plans[0].id).toBe(plan.id);
        }),
        { numRuns: 100 },
      );
    });

    it("rejects plans missing required fields", () => {
      const requiredFields = ["id", "name", "description", "author", "tags", "sections"];
      for (const field of requiredFields) {
        const plan: Record<string, unknown> = {
          id: "test",
          name: "Test",
          description: "desc",
          author: "me",
          tags: [],
          sections: [{ order: 1, problemNames: ["Two Sum"] }],
        };
        delete plan[field];
        const result = loadStudyPlans([plan], new Set(SAMPLE_NAMES));
        expect(result.plans.length).toBe(0);
      }
    });
  });

  // Feature: study-plans, Property 3: Cross-Reference Filtering
  describe("Property 3: Cross-Reference Filtering", () => {
    it("only keeps problem names that exist in the valid set", () => {
      fc.assert(
        fc.property(
          fc.array(fc.string({ minLength: 1, maxLength: 20 }), { minLength: 1, maxLength: 10 }),
          fc.subarray(SAMPLE_NAMES, { minLength: 1 }),
          (extraNames, validSubset) => {
            const allProblemNames = [...validSubset, ...extraNames];
            const validNames = new Set(SAMPLE_NAMES);
            const plan = {
              id: "test",
              name: "Test",
              description: "desc",
              author: "me",
              tags: [],
              sections: [{ order: 1, problemNames: allProblemNames }],
            };
            const result = loadStudyPlans([plan], validNames);
            if (result.plans.length > 0) {
              for (const section of result.plans[0].sections) {
                for (const name of section.problemNames) {
                  expect(validNames.has(name)).toBe(true);
                }
              }
              // Valid names that were in the input should still be present
              for (const name of validSubset) {
                const allOutputNames = result.plans[0].sections.flatMap((s) => s.problemNames);
                expect(allOutputNames).toContain(name);
              }
            }
          },
        ),
        { numRuns: 100 },
      );
    });
  });

  // Feature: study-plans, Property 4: Loader Validation Rules
  describe("Property 4: Loader Validation Rules", () => {
    it("keeps only first occurrence of duplicate IDs", () => {
      const plan1 = {
        id: "dup",
        name: "First",
        description: "d",
        author: "a",
        tags: [],
        sections: [{ order: 1, problemNames: ["Two Sum"] }],
      };
      const plan2 = {
        id: "dup",
        name: "Second",
        description: "d",
        author: "a",
        tags: [],
        sections: [{ order: 1, problemNames: ["3Sum"] }],
      };
      const result = loadStudyPlans([plan1, plan2], new Set(SAMPLE_NAMES));
      expect(result.plans.length).toBe(1);
      expect(result.plans[0].name).toBe("First");
      expect(result.warnings.some((w) => w.includes("Duplicate plan ID"))).toBe(true);
    });

    it("excludes sections with order < 1", () => {
      fc.assert(
        fc.property(
          fc.integer({ min: -100, max: 0 }),
          (badOrder) => {
            const plan = {
              id: "test",
              name: "Test",
              description: "d",
              author: "a",
              tags: [],
              sections: [
                { order: badOrder, problemNames: ["Two Sum"] },
                { order: 1, problemNames: ["3Sum"] },
              ],
            };
            const result = loadStudyPlans([plan], new Set(SAMPLE_NAMES));
            if (result.plans.length > 0) {
              expect(result.plans[0].sections.every((s) => s.order >= 1)).toBe(true);
            }
            expect(result.warnings.some((w) => w.includes("excluded (must be >= 1)"))).toBe(true);
          },
        ),
        { numRuns: 100 },
      );
    });

    it("excludes plans with no valid sections after filtering", () => {
      const plan = {
        id: "empty",
        name: "Empty",
        description: "d",
        author: "a",
        tags: [],
        sections: [{ order: 0, problemNames: ["Two Sum"] }],
      };
      const result = loadStudyPlans([plan], new Set(SAMPLE_NAMES));
      expect(result.plans.length).toBe(0);
      expect(result.warnings.some((w) => w.includes("no valid sections"))).toBe(true);
    });
  });
});

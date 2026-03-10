import { describe, it, expect, beforeEach } from "vitest";
import fc from "fast-check";
import { createPlanStore } from "../../lib/planStore";

beforeEach(() => {
  localStorage.clear();
});

describe("planStore", () => {
  it("initial state returns null", () => {
    const store = createPlanStore();
    expect(store.getActivePlanId()).toBeNull();
  });

  it("set then get returns the id", () => {
    const store = createPlanStore();
    store.setActivePlan("blind-75");
    expect(store.getActivePlanId()).toBe("blind-75");
  });

  it("clear then get returns null", () => {
    const store = createPlanStore();
    store.setActivePlan("blind-75");
    store.clearActivePlan();
    expect(store.getActivePlanId()).toBeNull();
  });

  it("isMemoryFallback is false when localStorage is available", () => {
    const store = createPlanStore();
    expect(store.isMemoryFallback).toBe(false);
  });

  // Feature: study-plans, Property 5: Plan Store Persistence Round-Trip
  // **Validates: Requirements 2.2, 2.5, 5.1, 5.3**
  describe("Property 5: Plan Store Persistence Round-Trip", () => {
    it("setActivePlan(id) then getActivePlanId() returns id", () => {
      fc.assert(
        fc.property(
          fc.string({ minLength: 1 }),
          (id) => {
            localStorage.clear();
            const store = createPlanStore();
            store.setActivePlan(id);
            expect(store.getActivePlanId()).toBe(id);
          },
        ),
        { numRuns: 100 },
      );
    });

    it("clearActivePlan() then getActivePlanId() returns null", () => {
      fc.assert(
        fc.property(
          fc.string({ minLength: 1 }),
          (id) => {
            localStorage.clear();
            const store = createPlanStore();
            store.setActivePlan(id);
            store.clearActivePlan();
            expect(store.getActivePlanId()).toBeNull();
          },
        ),
        { numRuns: 100 },
      );
    });

    it("persists across store instances", () => {
      fc.assert(
        fc.property(
          fc.string({ minLength: 1 }),
          (id) => {
            localStorage.clear();
            const store1 = createPlanStore();
            store1.setActivePlan(id);

            const store2 = createPlanStore();
            expect(store2.getActivePlanId()).toBe(id);
          },
        ),
        { numRuns: 100 },
      );
    });
  });
});

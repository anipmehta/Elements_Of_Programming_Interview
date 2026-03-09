import { describe, it, expect, beforeEach, vi } from "vitest";
import { createProgressStore } from "../../lib/progressStore";

beforeEach(() => {
  localStorage.clear();
  vi.restoreAllMocks();
});

describe("createProgressStore", () => {
  it("starts with empty completed set", () => {
    const store = createProgressStore();
    expect(store.getCompleted().size).toBe(0);
  });

  it("marks a problem as completed", () => {
    const store = createProgressStore();
    store.markCompleted("two_sum");
    expect(store.getCompleted().has("two_sum")).toBe(true);
  });

  it("marks a problem as incomplete", () => {
    const store = createProgressStore();
    store.markCompleted("two_sum");
    store.markIncomplete("two_sum");
    expect(store.getCompleted().has("two_sum")).toBe(false);
  });

  it("persists to localStorage", () => {
    const store = createProgressStore();
    store.markCompleted("two_sum");
    store.markCompleted("lru_cache");

    // Create a new store instance — should load from localStorage
    const store2 = createProgressStore();
    const completed = store2.getCompleted();
    expect(completed.has("two_sum")).toBe(true);
    expect(completed.has("lru_cache")).toBe(true);
  });

  it("handles multiple mark/unmark operations", () => {
    const store = createProgressStore();
    store.markCompleted("a");
    store.markCompleted("b");
    store.markCompleted("c");
    store.markIncomplete("b");
    const completed = store.getCompleted();
    expect(completed.has("a")).toBe(true);
    expect(completed.has("b")).toBe(false);
    expect(completed.has("c")).toBe(true);
    expect(completed.size).toBe(2);
  });

  it("returns a copy from getCompleted (not a reference)", () => {
    const store = createProgressStore();
    store.markCompleted("a");
    const set1 = store.getCompleted();
    store.markCompleted("b");
    const set2 = store.getCompleted();
    expect(set1.size).toBe(1);
    expect(set2.size).toBe(2);
  });

  it("handles corrupted localStorage data gracefully", () => {
    localStorage.setItem("study-tracker-completed", "not-json-array");
    const store = createProgressStore();
    expect(store.getCompleted().size).toBe(0);
  });

  it("handles non-array JSON in localStorage", () => {
    localStorage.setItem("study-tracker-completed", '{"a":1}');
    const store = createProgressStore();
    expect(store.getCompleted().size).toBe(0);
  });

  it("filters non-string values from localStorage", () => {
    localStorage.setItem("study-tracker-completed", '["a", 123, null, "b"]');
    const store = createProgressStore();
    const completed = store.getCompleted();
    expect(completed.has("a")).toBe(true);
    expect(completed.has("b")).toBe(true);
    expect(completed.size).toBe(2);
  });

  it("isMemoryFallback is false when localStorage is available", () => {
    const store = createProgressStore();
    expect(store.isMemoryFallback).toBe(false);
  });
});

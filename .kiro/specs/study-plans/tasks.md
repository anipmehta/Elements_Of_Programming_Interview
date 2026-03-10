# Implementation Plan: Study Plans

## Overview

Implement the Study Plans feature for AlgoForge in phased increments. Each phase is a self-contained unit that can be committed, pushed, and merged independently. The implementation starts with data types and pure logic (easily testable), then builds up through persistence, data fetching, UI components, and finally app-level integration.

## Tasks

- [x] 1. Define study plan types and implement the plan data loader
  - [x] 1.1 Create `web/src/lib/studyPlanTypes.ts` with `PlanSection`, `StudyPlan`, `RawPlanSection`, and `RawStudyPlan` interfaces
    - Define all types as specified in the design document Data Models section
    - Export all interfaces for use by loader, hook, and components
    - _Requirements: 1.1, 1.2, 9.4_

  - [x] 1.2 Create `web/src/lib/studyPlanLoader.ts` with the `loadStudyPlans` pure function
    - Implement `LoadResult` interface with `plans: StudyPlan[]` and `warnings: string[]`
    - Validate required fields and types on each raw plan object (`id`, `name`, `description`, `author`, `tags`, `sections`)
    - Validate each section has `order` (number >= 1), `problemNames` (string array), and optional `focus` (string)
    - Filter out plans with duplicate `id` values (keep first occurrence, add warning)
    - Filter out sections with `order < 1` (add warning)
    - Filter out plans with empty `sections` array after section filtering (add warning)
    - Remove invalid problem name references from sections using the `validProblemNames` set (add warning per removal)
    - All warnings use `[study-plans]` prefix format from design
    - _Requirements: 1.1, 1.2, 1.3, 1.4, 7.1, 7.2, 7.3, 7.4, 8.2_

  - [x] 1.3 Implement `computePlanProgress` and `isSectionComplete` helper functions in `web/src/lib/studyPlanLoader.ts`
    - `computePlanProgress(plan, completed)` returns `{ completed, total, percentage }`
    - `isSectionComplete(section, completed)` returns boolean
    - Handle edge case: total === 0 yields percentage 0
    - _Requirements: 3.2, 3.4_

  - [x]* 1.4 Write property tests for studyPlanLoader (Properties 1–4) in `web/src/__tests__/lib/studyPlanLoader.test.ts`
    - **Property 1: JSON Serialization Round-Trip** — For any valid StudyPlan, `JSON.parse(JSON.stringify(plan))` deep-equals the original
    - **Validates: Requirement 8.1**
    - **Property 2: Schema Validation Accepts Valid and Rejects Invalid** — `loadStudyPlans` returns valid plans iff all required fields present with correct types
    - **Validates: Requirements 1.1, 1.2, 7.1, 8.2, 9.2**
    - **Property 3: Cross-Reference Filtering** — After loading, every problemName in every section is in the valid set; no valid name originally present is removed
    - **Validates: Requirements 1.3, 1.4**
    - **Property 4: Loader Validation Rules** — Duplicate IDs keep first only; sections with order < 1 excluded; plans with no valid sections excluded
    - **Validates: Requirements 7.2, 7.3, 7.4**

  - [x]* 1.5 Write property tests for plan progress functions (Properties 6–7) in `web/src/__tests__/lib/planProgress.test.ts`
    - **Property 6: Section Completion Correctness** — `isSectionComplete` returns true iff every problemName is in the completed set and section is non-empty
    - **Validates: Requirement 3.2**
    - **Property 7: Plan Progress Computation** — `computePlanProgress` returns correct completed count, total, and percentage
    - **Validates: Requirement 3.4**

- [x] 2. Checkpoint — Ensure all tests pass
  - Ensure all tests pass, ask the user if questions arise.

- [x] 3. Implement plan store and study plans hook
  - [x] 3.1 Create `web/src/lib/planStore.ts` implementing the `PlanStore` interface
    - Implement `getActivePlanId()`, `setActivePlan(id)`, `clearActivePlan()`, and `isMemoryFallback` property
    - Use localStorage key `"study-plans-active"` for persistence
    - Mirror the `progressStore` pattern: detect localStorage availability, fall back to in-memory
    - Export `createPlanStore` factory function
    - _Requirements: 2.2, 2.5, 4.4, 5.1, 5.2, 5.3_

  - [x]* 3.2 Write property tests for planStore (Property 5) in `web/src/__tests__/lib/planStore.test.ts`
    - **Property 5: Plan Store Persistence Round-Trip** — `setActivePlan(id)` then `getActivePlanId()` returns `id`; `clearActivePlan()` then `getActivePlanId()` returns `null`
    - **Validates: Requirements 2.2, 2.5, 5.1, 5.3**

  - [x] 3.3 Create `web/src/hooks/useStudyPlans.ts` hook
    - Fetch `study-plans.json` using `import.meta.env.BASE_URL` prefix (same pattern as `useProblems`)
    - Call `loadStudyPlans` with parsed JSON and valid problem names set built from `ProblemEntry[]` parameter
    - Log each warning from `LoadResult.warnings` via `console.warn`
    - Return `{ plans, loading, error }` matching `UseStudyPlansResult` interface
    - Handle fetch errors and invalid JSON gracefully
    - _Requirements: 1.3, 1.4, 1.5_

  - [x]* 3.4 Write unit tests for `useStudyPlans` hook in `web/src/__tests__/hooks/useStudyPlans.test.ts` (extend or create new file)
    - Test successful fetch and validation
    - Test fetch error handling
    - Test that warnings are logged to console
    - _Requirements: 1.3, 1.4_

- [x] 4. Checkpoint — Ensure all tests pass
  - Ensure all tests pass, ask the user if questions arise.

- [x] 5. Create the study-plans.json seed data file
  - [x] 5.1 Create `study-plans.json` at the repo root with at least one sample study plan
    - Include a "Blind 75" style plan with multiple sections referencing real problem names from `problems.json`
    - Ensure all `problemNames` entries reference valid Problem_Entry names
    - Follow the schema: `id`, `name`, `description`, `author`, `tags`, `sections` with `order`, `focus`, `problemNames`
    - _Requirements: 1.1, 1.2, 1.5, 9.2, 9.5_

- [x] 6. Build PlanSelector and PlanView components
  - [x] 6.1 Create `web/src/components/PlanSelector.tsx`
    - Accept `PlanSelectorProps`: `plans`, `activePlanId`, `onSelectPlan`, `onDeactivatePlan`
    - Render a list of plan cards showing name, description, author, and tags
    - Highlight the active plan and show a "Deactivate" button on it
    - Non-active plans show a "Select" button
    - _Requirements: 2.1, 2.3, 2.4_

  - [x] 6.2 Create `web/src/components/PlanView.tsx`
    - Accept `PlanViewProps`: `plan`, `problems`, `completed`, `onToggle`
    - Render overall progress bar at the top using `computePlanProgress`
    - Render each section in order with focus description, section completion indicator
    - Render each problem with name, difficulty, pattern, source URL link, and completion checkbox
    - Checkbox toggle calls `onToggle(problemName)`
    - _Requirements: 3.1, 3.2, 3.3, 3.4, 3.5, 4.1_

  - [x]* 6.3 Write unit tests for PlanSelector in `web/src/__tests__/components/PlanSelector.test.tsx`
    - Test rendering of plan cards with name, description, author, tags
    - Test select and deactivate button interactions
    - _Requirements: 2.1, 2.3, 2.4_

  - [x]* 6.4 Write unit tests for PlanView in `web/src/__tests__/components/PlanView.test.tsx`
    - Test section rendering in order with focus descriptions
    - Test progress bar display
    - Test problem toggle interaction calls onToggle
    - Test section completion indicator
    - _Requirements: 3.1, 3.2, 3.3, 3.4, 3.5_

- [x] 7. Checkpoint — Ensure all tests pass
  - Ensure all tests pass, ask the user if questions arise.

- [x] 8. Integrate study plans into App.tsx with view navigation
  - [x] 8.1 Add view toggle state and plan state management to `App.tsx`
    - Add `view` state: `"problems" | "plans"` defaulting to `"problems"`
    - Initialize `planStore` via `useMemo` and `activePlanId` state from `planStore.getActivePlanId()`
    - Call `useStudyPlans(problems)` to get validated plans
    - Implement `handleSelectPlan` and `handleDeactivatePlan` callbacks that update both `planStore` and React state
    - _Requirements: 2.2, 2.5, 5.1, 6.4_

  - [x] 8.2 Add navigation UI and conditional view rendering in `App.tsx`
    - Add a nav element with two buttons/tabs to switch between "Problems" and "Study Plans" views
    - When `view === "problems"`: render FilterBar, StatusBar, StatsPanel, ProblemTable (existing)
    - When `view === "plans"`: render PlanSelector and conditionally PlanView (when a plan is active)
    - Pass `completed` set and `handleToggle` to PlanView so progress syncs bidirectionally
    - Show `planStore.isMemoryFallback` warning in plans view (same pattern as existing progressStore warning)
    - _Requirements: 4.1, 4.2, 4.3, 6.1, 6.2, 6.3_

  - [x] 8.3 Add CSS styles for study plan components in `web/src/index.css`
    - Style the navigation tabs/buttons
    - Style PlanSelector plan cards (name, description, author, tags, active highlight)
    - Style PlanView sections, progress bar, section completion indicators, and problem rows
    - _Requirements: 2.1, 3.1, 3.2, 3.4, 6.1_

  - [ ]* 8.4 Write integration tests for view toggle and state preservation in `web/src/__tests__/App.studyPlans.test.tsx`
    - **Property 8: View Exclusivity** — For any view state, exactly one view group is rendered
    - **Validates: Requirements 6.2, 6.3**
    - **Property 9: View Switch Preserves State** — Switching views and back preserves filter, completion, and active plan state
    - **Validates: Requirement 6.4**
    - Test that toggling a problem in PlanView updates ProblemTable's completed set and vice versa
    - _Requirements: 4.2, 4.3_

- [x] 9. Final checkpoint — Ensure all tests pass
  - Ensure all tests pass, ask the user if questions arise.

## Notes

- Tasks marked with `*` are optional and can be skipped for faster MVP
- Each phase (between checkpoints) is designed to be committed and merged independently
- Property tests use `fast-check` as specified in the design document
- All components reuse the existing `handleToggle` callback in App.tsx for bidirectional progress sync
- The `study-plans.json` file is fetched at runtime like `problems.json` — no build-time bundling

# Implementation Plan: Study Tracker UI

## Overview

Build a static React + Vite + TypeScript dashboard in `web/` that reads `problems.json` and provides filtering by pattern, difficulty, and company. Phase 1 covers stack setup, data loading, filtering, and UI components. Phase 2 covers GitHub Pages deployment. Phase 3 adds localStorage progress tracking and completion stats.

## Phase 1 Tasks

- [x] 1. Scaffold Vite + React + TypeScript project
  - [x] 1.1 Initialize the `web/` directory with Vite React-TS template
    - Run `npm create vite@latest web -- --template react-ts` (or equivalent manual setup)
    - Ensure `package.json`, `tsconfig.json`, `vite.config.ts`, `index.html`, `src/main.tsx` are created
    - _Requirements: 9.1, 9.2_
  - [x] 1.2 Configure Vite for GitHub Pages
    - Set `base` in `vite.config.ts` to `/<repo-name>/` for correct asset paths on GitHub Pages
    - Verify `vite build` outputs to `web/dist/`
    - _Requirements: 9.2, 9.3_
  - [x] 1.3 Copy `problems.json` into `web/public/`
    - Copy the root `problems.json` into `web/public/problems.json` so Vite serves it as a static asset
    - _Requirements: 1.1_

- [x] 2. Define core types and pure filter logic
  - [x] 2.1 Create `src/lib/types.ts` with `ProblemEntry` and `FilterState` interfaces
    - `ProblemEntry`: name, pattern, subPattern, difficulty, companies, path, sourceUrl
    - `FilterState`: pattern (string | null), difficulty (string | null), company (string | null)
    - _Requirements: 1.4_
  - [x] 2.2 Implement filter functions in `src/lib/filter.ts`
    - `applyFilters(problems, filters)`: returns entries matching ALL active filters (AND logic), null fields mean no constraint
    - `extractPatterns(problems)`: returns sorted unique pattern values
    - `extractCompanies(problems)`: returns sorted unique company names flattened from all companies arrays
    - _Requirements: 2.1, 2.2, 2.3, 3.2, 3.3, 4.1, 4.2, 4.3, 5.1_

  - [ ]* 2.3 Write property tests for filter functions (fast-check)
    - **Property 1: Filter correctness (AND semantics)** — for any problems array and any FilterState, `applyFilters` returns exactly the entries matching all active filters
    - **Validates: Requirements 1.2, 2.2, 2.3, 3.2, 3.3, 4.2, 4.3, 5.1**
  - [ ]* 2.4 Write property test for pattern extraction
    - **Property 2: Pattern extraction completeness** — `extractPatterns` returns sorted distinct pattern values, no duplicates, no missing, no invented
    - **Validates: Requirements 2.1**
  - [ ]* 2.5 Write property test for company extraction
    - **Property 3: Company extraction completeness** — `extractCompanies` returns sorted distinct company names from all companies arrays
    - **Validates: Requirements 4.1**

- [x] 3. Checkpoint — Verify core logic
  - Ensure all tests pass, ask the user if questions arise.

- [x] 4. Implement data fetching hook
  - [x] 4.1 Create `src/hooks/useProblems.ts`
    - Fetch `problems.json` from public directory on mount
    - Return `{ problems: ProblemEntry[], loading: boolean, error: string | null }`
    - Handle fetch failures and invalid JSON by setting error state
    - _Requirements: 1.1, 1.2, 1.3_
  - [ ]* 4.2 Write unit tests for useProblems hook
    - Test successful fetch returns parsed ProblemEntry array
    - Test fetch failure sets error state
    - Test invalid JSON sets error state
    - _Requirements: 1.1, 1.2, 1.3_

- [x] 5. Build UI components
  - [x] 5.1 Create `src/components/FilterBar.tsx`
    - Three `<select>` dropdowns: pattern, difficulty, company
    - Each dropdown has an "All" default option
    - Pattern and company dropdowns populated from extracted values
    - Difficulty dropdown has fixed options: Easy, Medium, Hard
    - On mobile (< 768px), dropdowns stack vertically via CSS
    - _Requirements: 2.1, 3.1, 4.1, 10.2_
  - [x] 5.2 Create `src/components/ProblemTable.tsx`
    - Render `<table>` with columns: Name, Pattern, Sub-Pattern, Difficulty, Companies
    - Name column: render as `<a href={sourceUrl} target="_blank" rel="noopener noreferrer">` if sourceUrl !== "TBD", otherwise plain text
    - subPattern null renders as "—"
    - Table container gets `overflow-x: auto` for mobile horizontal scroll
    - _Requirements: 1.4, 6.1, 6.2, 10.1, 10.2_
  - [x] 5.3 Create `src/components/StatusBar.tsx`
    - Render "Showing {visibleCount} of {totalCount} problems"
    - _Requirements: 5.2_

- [x] 6. Wire everything together in App.tsx
  - [x] 6.1 Implement `src/App.tsx` as root component
    - Use `useProblems()` to fetch data on mount
    - Own `FilterState` in component state, initialized to all nulls
    - Derive `filteredProblems` via `applyFilters(problems, filters)`
    - Derive dropdown options via `extractPatterns` and `extractCompanies`
    - Render loading state, error state, or FilterBar + StatusBar + ProblemTable
    - _Requirements: 1.1, 1.2, 1.3, 2.2, 3.2, 4.2, 5.1, 5.2_
  - [x] 6.2 Add base styles in `src/index.css`
    - Minimal responsive styles: table layout, filter bar stacking on mobile, horizontal scroll
    - _Requirements: 10.1, 10.2_

  - [ ]* 6.3 Write property test for visible/total count invariant
    - **Property 4: Visible and total count invariant** — visible count equals length of `applyFilters` result, total count equals length of original array
    - **Validates: Requirements 5.2**
  - [ ]* 6.4 Write unit tests for ProblemTable source link rendering
    - **Property 5: Source link rendering** — name rendered as hyperlink iff sourceUrl !== "TBD", link opens in new tab
    - **Validates: Requirements 6.1, 6.2**

- [x] 7. Checkpoint — Phase 1 complete
  - Ensure all tests pass, ask the user if questions arise.
  - Verify the app builds with `npm run build` and all asset paths resolve correctly
  - Verify `problems.json` is included in the build output

## Phase 2 Tasks (GitHub Pages Deployment)

- [ ] 8. Set up GitHub Actions workflow for Pages deployment
  - [ ] 8.1 Create `.github/workflows/deploy-pages.yml`
    - Trigger on push to `master`
    - Install Node deps and run `npm run build` in `web/`
    - Deploy `web/dist/` to GitHub Pages using `actions/deploy-pages`
    - _Requirements: 9.2, 9.3_
  - [ ] 8.2 Verify deployment
    - Confirm the site is live at `https://anipmehta.github.io/Elements_Of_Programming_Interview/`
    - Verify `problems.json` loads correctly and filters work on the deployed site
    - _Requirements: 9.3_

- [ ] 9. Checkpoint — Phase 2 complete
  - Site is live on GitHub Pages, auto-deploys on push to master

## Phase 3 Tasks (Deferred — Progress Tracking & Stats)

- [ ] 10. Implement localStorage progress store
  - [ ] 10.1 Create `src/lib/progressStore.ts`
    - Implement `ProgressStore` interface: `getCompleted()`, `markCompleted(name)`, `markIncomplete(name)`
    - Store completed problem names as JSON array under key `study-tracker-completed` in localStorage
    - Handle localStorage unavailable: fall back to in-memory set, return a warning flag
    - Handle corrupted data: reset to empty set, return a warning flag
    - _Requirements: 7.2, 7.3, 7.4, 7.5_
  - [ ]* 10.2 Write property test for ProgressStore round-trip
    - **Property 6: ProgressStore round-trip** — for any sequence of markCompleted/markIncomplete operations, getCompleted returns exactly the names marked completed and not subsequently marked incomplete
    - **Validates: Requirements 7.2, 7.3, 7.4**
  - [ ]* 10.3 Write unit tests for ProgressStore edge cases
    - Test localStorage unavailable shows warning
    - Test corrupted localStorage data resets gracefully
    - _Requirements: 7.5_

- [ ] 11. Add completion checkbox to ProblemTable
  - [ ] 11.1 Extend `ProblemTable` to accept completion state and toggle callback
    - Add a checkbox column to each row
    - On check: call `markCompleted(name)`, on uncheck: call `markIncomplete(name)`
    - On load: reflect saved completion state from `ProgressStore.getCompleted()`
    - _Requirements: 7.1, 7.2, 7.3, 7.4_
  - [ ]* 11.2 Write unit tests for checkbox toggle behavior
    - Test checking a problem persists to localStorage
    - Test unchecking removes from localStorage
    - _Requirements: 7.1, 7.2, 7.3_

- [ ] 12. Implement StatsPanel component
  - [ ] 12.1 Create `src/lib/stats.ts` with pure stats computation functions
    - Compute overall completion percentage
    - Compute per-pattern completion count and percentage
    - Compute per-difficulty completion count and percentage
    - _Requirements: 8.1, 8.2, 8.3_
  - [ ]* 12.2 Write property test for stats computation
    - **Property 7: Stats computation correctness** — overall percentage = completedCount/totalCount, per-pattern and per-difficulty counts match entries in completed set
    - **Validates: Requirements 8.1, 8.2, 8.3**
  - [ ] 12.3 Create `src/components/StatsPanel.tsx`
    - Display overall completion percentage
    - Display breakdown by pattern (count and percentage)
    - Display breakdown by difficulty (count and percentage)
    - Update immediately when completion state changes (no page reload)
    - _Requirements: 8.1, 8.2, 8.3, 8.4_

- [ ] 13. Wire Phase 3 into App.tsx
  - [ ] 13.1 Integrate ProgressStore and StatsPanel into App
    - Initialize ProgressStore on mount, pass completion state to ProblemTable and StatsPanel
    - Display warning if localStorage is unavailable
    - _Requirements: 7.4, 7.5, 8.4_

- [ ] 14. Final checkpoint — Phase 3 complete
  - Ensure all tests pass, ask the user if questions arise.
  - Verify localStorage persistence works across page reloads

## Notes

- Tasks marked with `*` are optional and can be skipped for faster MVP
- Phase 1 (tasks 1–7) should be completed first; Phase 2 (tasks 8–9) covers GitHub Pages deployment; Phase 3 (tasks 10–14) can be deferred
- Each task references specific requirements for traceability
- Property tests use fast-check and target pure logic in `lib/` (no DOM required)
- Follow CLAUDE.md rules: feature branch, conventional commits, compile before committing

# Requirements Document

## Introduction

A lightweight, static React dashboard for an existing coding interview prep repository. The dashboard reads from `problems.json` (115 problems across 12 pattern-based categories) and provides filtering, search, and progress tracking. It deploys to GitHub Pages with zero backend — all state lives in localStorage.

The feature is delivered in two phases:
- **Phase 1**: React stack setup, problem dashboard with filtering by pattern, difficulty, and company.
- **Phase 2**: localStorage-based progress tracking, visual completion stats, and breakdown charts.

## Glossary

- **Dashboard**: The main page of the React application displaying the problem table and filter controls.
- **Problem_Table**: The UI component that renders the list of problems with their metadata columns.
- **Filter_Bar**: The UI component containing dropdown and input controls for narrowing the problem list.
- **Progress_Store**: The localStorage-backed persistence layer that stores which problems a user has marked as completed.
- **Stats_Panel**: The UI component displaying completion percentages and breakdowns by pattern and difficulty.
- **Problem_Entry**: A single object in `problems.json` with fields: name, pattern, subPattern, difficulty, companies, path, sourceUrl.
- **Pattern**: One of the 12 algorithm categories (e.g., arrays_and_strings, dynamic_programming, graphs).
- **Difficulty**: One of three levels — Easy, Medium, Hard.

## Requirements

### Requirement 1: Load Problem Data

**User Story:** As a user, I want the dashboard to load all problems from `problems.json` at startup, so that I can browse the full problem set without a backend.

#### Acceptance Criteria

1. WHEN the Dashboard loads, THE Dashboard SHALL fetch and parse `problems.json` from the application's public assets.
2. WHEN `problems.json` is successfully parsed, THE Dashboard SHALL display all Problem_Entry records in the Problem_Table.
3. IF `problems.json` fails to load or contains invalid JSON, THEN THE Dashboard SHALL display an error message indicating the data could not be loaded.
4. THE Problem_Table SHALL display the following columns for each Problem_Entry: name, pattern, subPattern, difficulty, companies, and a link to sourceUrl.

### Requirement 2: Filter by Pattern

**User Story:** As a user, I want to filter problems by algorithm pattern, so that I can focus my study on a specific category.

#### Acceptance Criteria

1. THE Filter_Bar SHALL provide a pattern dropdown populated with all distinct Pattern values extracted from the loaded Problem_Entry records.
2. WHEN the user selects a Pattern from the dropdown, THE Problem_Table SHALL display only Problem_Entry records matching the selected Pattern.
3. WHEN the user clears the pattern filter, THE Problem_Table SHALL display all Problem_Entry records (subject to other active filters).

### Requirement 3: Filter by Difficulty

**User Story:** As a user, I want to filter problems by difficulty level, so that I can study problems appropriate to my current skill level.

#### Acceptance Criteria

1. THE Filter_Bar SHALL provide a difficulty dropdown with the options: Easy, Medium, Hard.
2. WHEN the user selects a Difficulty from the dropdown, THE Problem_Table SHALL display only Problem_Entry records matching the selected Difficulty.
3. WHEN the user clears the difficulty filter, THE Problem_Table SHALL display all Problem_Entry records (subject to other active filters).

### Requirement 4: Filter by Company

**User Story:** As a user, I want to filter problems by company, so that I can prepare for interviews at a specific company.

#### Acceptance Criteria

1. THE Filter_Bar SHALL provide a company dropdown populated with all distinct company names extracted from the loaded Problem_Entry records.
2. WHEN the user selects a company from the dropdown, THE Problem_Table SHALL display only Problem_Entry records whose companies array contains the selected company.
3. WHEN the user clears the company filter, THE Problem_Table SHALL display all Problem_Entry records (subject to other active filters).

### Requirement 5: Combined Filtering

**User Story:** As a user, I want to apply multiple filters simultaneously, so that I can narrow down problems precisely.

#### Acceptance Criteria

1. WHEN multiple filters are active (pattern, difficulty, company), THE Problem_Table SHALL display only Problem_Entry records that satisfy all active filter conditions (logical AND).
2. THE Dashboard SHALL display a count of the currently visible Problem_Entry records alongside the total count.


### Requirement 6: Problem Source Links

**User Story:** As a user, I want each problem to link to its original source, so that I can navigate directly to the problem on LeetCode or other platforms.

#### Acceptance Criteria

1. WHEN a Problem_Entry has a valid sourceUrl (not "TBD"), THE Problem_Table SHALL render the problem name as a clickable link that opens the sourceUrl in a new browser tab.
2. WHEN a Problem_Entry has a sourceUrl of "TBD", THE Problem_Table SHALL render the problem name as plain text without a link.

### Requirement 7: Progress Tracking via localStorage (Phase 2)

**User Story:** As a user, I want to mark problems as completed and have that progress persist across browser sessions, so that I can track my study over time.

#### Acceptance Criteria

1. THE Problem_Table SHALL display a checkbox for each Problem_Entry to toggle completion status.
2. WHEN the user checks a Problem_Entry checkbox, THE Progress_Store SHALL save the problem name as completed in localStorage.
3. WHEN the user unchecks a Problem_Entry checkbox, THE Progress_Store SHALL remove the problem name from the completed set in localStorage.
4. WHEN the Dashboard loads, THE Progress_Store SHALL read previously saved completion data from localStorage and THE Problem_Table SHALL reflect the saved completion state for each Problem_Entry.
5. IF localStorage is unavailable or corrupted, THEN THE Dashboard SHALL operate with an empty completion set and display a warning that progress cannot be saved.

### Requirement 8: Completion Statistics (Phase 2)

**User Story:** As a user, I want to see visual statistics of my progress, so that I can understand how much I have covered and where gaps remain.

#### Acceptance Criteria

1. THE Stats_Panel SHALL display the overall completion percentage as completed Problem_Entry count divided by total Problem_Entry count.
2. THE Stats_Panel SHALL display a breakdown of completion count and percentage for each Pattern.
3. THE Stats_Panel SHALL display a breakdown of completion count and percentage for each Difficulty level (Easy, Medium, Hard).
4. WHEN the user marks or unmarks a Problem_Entry as completed, THE Stats_Panel SHALL update all displayed statistics immediately without a page reload.

### Requirement 9: GitHub Pages Deployment

**User Story:** As a developer, I want the app to deploy to GitHub Pages, so that the dashboard is accessible without hosting infrastructure.

#### Acceptance Criteria

1. THE Dashboard SHALL be a static single-page application that requires no server-side runtime.
2. THE Dashboard SHALL include a build configuration that produces static assets deployable to GitHub Pages.
3. WHEN deployed to GitHub Pages, THE Dashboard SHALL correctly resolve all asset paths including `problems.json`.

### Requirement 10: Responsive Layout

**User Story:** As a user, I want the dashboard to be usable on both desktop and mobile screens, so that I can review problems from any device.

#### Acceptance Criteria

1. THE Dashboard SHALL render the Problem_Table and Filter_Bar in a readable layout on viewports 320px wide and above.
2. WHILE the viewport width is below 768px, THE Dashboard SHALL stack the Filter_Bar controls vertically and allow horizontal scrolling on the Problem_Table if needed.

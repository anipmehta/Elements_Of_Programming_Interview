# Requirements Document

## Introduction

Study Plans adds curated, structured problem sequences to AlgoForge so users can follow pattern-based interview prep paths (e.g., "Blind 75", "Amazon top 20", "Trees deep dive"). Plans are organized into ordered sections, each with a theme and a set of problems. Users progress at their own pace — there is no calendar or schedule enforcement. Progress is measured by how many problems in the plan have been completed. Plans are defined in a JSON data file, rendered in the web UI with progress tracking integration, and structured for future consumption by an MCP server. The schema is designed for easy community contribution via pull requests.

## Glossary

- **Study_Plan**: A named, ordered collection of Plan_Sections designed to guide a user through a focused interview prep path.
- **Plan_Section**: An ordered group within a Study_Plan, containing one or more problem references and an optional focus description. Sections have a sequence order but carry no calendar or time-based semantics.
- **Plan_Selector**: The UI component that allows users to browse and activate a Study_Plan.
- **Plan_View**: The UI component that displays the active Study_Plan's sections with progress indicators.
- **Plan_Store**: The persistence layer (localStorage) that tracks which Study_Plan is active and per-plan completion state.
- **Plan_Data_File**: The JSON file (`study-plans.json`) at the repo root that defines all available Study_Plans.
- **Progress_Store**: The existing localStorage-based module (`progressStore.ts`) that tracks problem completion across the application.
- **Problem_Entry**: An existing problem record from `problems.json`, identified by its `name` field.
- **Plan_Author**: A string field identifying the contributor or source that created a Study_Plan, used for community attribution.

## Requirements

### Requirement 1: Study Plan Data Schema

**User Story:** As a contributor, I want study plans defined in a structured JSON file with a clear, well-documented schema, so that plans are easy to author, validate, and consume by both the web UI and future MCP integrations.

#### Acceptance Criteria

1. THE Plan_Data_File SHALL contain an array of Study_Plan objects, each with a unique `id` (string), `name` (string), `description` (string), `author` (string identifying the Plan_Author), `tags` (string array), and a `sections` array of Plan_Section objects.
2. Each Plan_Section object in the Plan_Data_File SHALL contain an `order` (number defining sequence position), `problemNames` (string array referencing Problem_Entry name fields), and an optional `focus` (string) describing the section's theme.
3. WHEN the Plan_Data_File is loaded, THE Plan_Selector SHALL validate that every `problemNames` entry in every Plan_Section references a valid Problem_Entry name from `problems.json`.
4. IF a Plan_Section references a Problem_Entry name that does not exist in `problems.json`, THEN THE Plan_Selector SHALL exclude that problem from the section's list and log a warning to the console.
5. THE Plan_Data_File SHALL be a standalone JSON file at the repo root (`study-plans.json`), separate from `problems.json`.

### Requirement 2: Study Plan Browsing and Selection

**User Story:** As a user, I want to browse available study plans and select one to follow, so that I can choose a prep path that fits my goals.

#### Acceptance Criteria

1. THE Plan_Selector SHALL display all available Study_Plans with their name, description, author, and tags.
2. WHEN a user selects a Study_Plan, THE Plan_Store SHALL persist the selected plan's `id` as the active plan in localStorage.
3. WHEN a user selects a Study_Plan, THE Plan_View SHALL become visible and display the selected plan's sections.
4. THE Plan_Selector SHALL allow the user to deactivate the current Study_Plan and return to browsing mode.
5. WHEN the application loads, THE Plan_Store SHALL restore the previously active Study_Plan if one was persisted.

### Requirement 3: Section-Based Plan View

**User Story:** As a user, I want to see my active study plan as an ordered list of themed sections with problem links and completion status, so that I can work through the plan at my own pace and track my overall progress.

#### Acceptance Criteria

1. THE Plan_View SHALL display each Plan_Section as a distinct group showing the section order, optional focus description, and the list of problems for that section.
2. WHEN all problems in a Plan_Section are marked as completed in the Progress_Store, THE Plan_View SHALL visually indicate that section as complete.
3. THE Plan_View SHALL display each problem's name, difficulty, and pattern alongside a link to the problem's source URL.
4. THE Plan_View SHALL display an overall plan progress indicator showing the count and percentage of completed problems out of the total problems in the Study_Plan.
5. WHEN a user clicks a problem's completion toggle in the Plan_View, THE Progress_Store SHALL update the problem's completion status (consistent with the existing toggle behavior in ProblemTable).

### Requirement 4: Plan Progress Tracking Integration

**User Story:** As a user, I want my study plan progress to stay in sync with the global progress tracker, so that completing a problem in any view updates all views consistently.

#### Acceptance Criteria

1. THE Plan_View SHALL read completion status from the same Progress_Store used by the ProblemTable component.
2. WHEN a problem is marked complete in the ProblemTable, THE Plan_View SHALL reflect the updated completion status for that problem without requiring a page reload.
3. WHEN a problem is marked complete in the Plan_View, THE ProblemTable SHALL reflect the updated completion status for that problem without requiring a page reload.
4. THE Plan_Store SHALL persist plan-specific state (active plan ID) separately from the Progress_Store's completion data, using a distinct localStorage key.

### Requirement 5: Plan Progress Persistence

**User Story:** As a user, I want my active plan selection and progress to persist across browser sessions, so that I can close the browser and resume my plan later.

#### Acceptance Criteria

1. WHEN the application loads, THE Plan_Store SHALL restore the active Study_Plan ID from localStorage.
2. IF localStorage is unavailable, THEN THE Plan_Store SHALL use an in-memory fallback and display a warning to the user (consistent with the existing Progress_Store fallback behavior).
3. WHEN the user deactivates a Study_Plan, THE Plan_Store SHALL remove the active plan ID from localStorage.

### Requirement 6: Plan Navigation in App Layout

**User Story:** As a user, I want to navigate between the main problem table view and the study plans view, so that I can switch contexts without losing state.

#### Acceptance Criteria

1. THE App SHALL provide a navigation mechanism allowing the user to switch between the problem table view and the study plans view.
2. WHILE the study plans view is active, THE App SHALL hide the FilterBar, StatusBar, and ProblemTable components.
3. WHILE the problem table view is active, THE App SHALL hide the Plan_Selector and Plan_View components.
4. WHEN the user switches views, THE App SHALL preserve filter state, completion state, and active plan state.

### Requirement 7: Study Plan Data File Validation

**User Story:** As a contributor, I want the study plans JSON to be validated, so that malformed plans are caught before they reach users.

#### Acceptance Criteria

1. THE Plan_Data_File SHALL conform to a JSON schema that enforces required fields (`id`, `name`, `description`, `author`, `tags`, `sections`) and correct types.
2. WHEN the Plan_Data_File contains a Study_Plan with duplicate `id` values, THE Plan_Selector SHALL use only the first occurrence and log a warning to the console.
3. WHEN the Plan_Data_File contains a Plan_Section with an `order` number less than 1, THE Plan_Selector SHALL exclude that Plan_Section and log a warning to the console.
4. WHEN the Plan_Data_File contains a Study_Plan with an empty `sections` array, THE Plan_Selector SHALL exclude that Study_Plan from the available plans list.

### Requirement 8: Study Plan JSON Serialization Round-Trip

**User Story:** As a developer, I want to ensure the study plan data format is reliable, so that plans can be serialized and deserialized without data loss.

#### Acceptance Criteria

1. FOR ALL valid Study_Plan objects, parsing the Plan_Data_File JSON then serializing back to JSON then parsing again SHALL produce an equivalent object (round-trip property).
2. THE Plan_Data_File parser SHALL accept any valid JSON that conforms to the Study_Plan schema and reject JSON that does not conform.

### Requirement 9: Community Contribution and Extensibility

**User Story:** As a community contributor, I want a simple, well-documented plan schema with author attribution, so that I can fork the repo, add a new study plan to `study-plans.json`, and submit a pull request.

#### Acceptance Criteria

1. THE Plan_Data_File schema SHALL be documented in the repository README or a dedicated contributing guide, including field descriptions, types, required vs. optional fields, and an example Study_Plan entry.
2. Each Study_Plan object in the Plan_Data_File SHALL include an `author` field (string) for community attribution.
3. THE Plan_Data_File schema SHALL use the same structure that a future MCP server can consume to generate plans programmatically.
4. THE Plan_Data_File schema SHALL require only fields necessary for plan rendering (`id`, `name`, `description`, `author`, `tags`, `sections`), keeping the barrier to contribution low.
5. WHEN a contributor adds a new Study_Plan to the Plan_Data_File, THE Plan_Selector SHALL display the new plan without requiring code changes to the web application.

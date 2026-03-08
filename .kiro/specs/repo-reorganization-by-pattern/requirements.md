# Requirements Document

## Introduction

This feature reorganizes an existing Java coding interview practice repository (~70 problems) from a flat company-based/problem-name folder structure into a pattern-based hierarchy. The goal is to teach algorithmic patterns over problem memorization, making the repository more valuable for the community as an interview preparation resource. Problems are classified by technique (e.g., sliding window, DFS, dynamic programming), company tags are preserved via an index, and each pattern folder includes educational material explaining the technique.

## Glossary

- **Repository**: The Java coding interview practice Git repository containing ~70 problems under `src/`
- **Problem_Folder**: A directory containing a `README.md` and one or more `.java` solution files for a single coding problem
- **Pattern_Folder**: A top-level numbered directory (e.g., `01_arrays_and_strings/`) representing an algorithmic pattern or data structure category
- **Sub_Pattern_Folder**: A subdirectory within a Pattern_Folder representing a specific technique (e.g., `sliding_window/` within `01_arrays_and_strings/`)
- **PATTERN_MD**: A markdown file (`PATTERN.md`) placed in each Pattern_Folder that explains the technique, common approaches, and when to apply the pattern
- **Problem_README**: The `README.md` file within each Problem_Folder containing the problem statement, examples, analysis, and metadata tags
- **Company_Index**: A root-level `COMPANY_INDEX.md` file that maps company names to the problems historically asked by each company
- **Root_README**: The top-level `README.md` file serving as a table of contents with study order and progress tracking
- **Util_Folder**: The shared `util/` directory containing reusable Java classes (TreeNode, Trie, etc.)
- **Difficulty_Tag**: A metadata label (Easy, Medium, Hard) added to each Problem_README
- **Company_Tag**: A metadata label indicating which company has asked the problem in interviews
- **Reorganization_Script**: An optional automation script or manual process that moves Problem_Folders from the old structure into the new pattern-based structure
- **Problems_JSON**: A root-level `problems.json` file containing a machine-readable index of all problems with their metadata, used as a data source for a future frontend

## Requirements

### Requirement 1: Pattern-Based Directory Structure

**User Story:** As a developer studying for interviews, I want problems organized by algorithmic pattern, so that I can study techniques systematically rather than memorizing individual problems.

#### Acceptance Criteria

1. THE Repository SHALL contain the following top-level numbered Pattern_Folders under `src/`: `01_arrays_and_strings`, `02_linked_lists`, `03_stacks_and_queues`, `04_trees`, `05_graphs`, `06_recursion_and_backtracking`, `07_dynamic_programming`, `08_binary_search`, `09_heaps_and_priority_queues`, `10_tries`, `11_design`, `12_greedy`
2. THE Pattern_Folder `01_arrays_and_strings` SHALL contain Sub_Pattern_Folders: `two_pointers`, `sliding_window`, `prefix_sum`
3. THE Pattern_Folder `03_stacks_and_queues` SHALL contain Sub_Pattern_Folders: `monotonic_stack`, `basic`
4. THE Pattern_Folder `04_trees` SHALL contain Sub_Pattern_Folders: `dfs`, `bfs`, `construction`
5. THE Pattern_Folder `05_graphs` SHALL contain Sub_Pattern_Folders: `dfs_bfs`, `topological_sort`, `union_find`, `shortest_path`
6. THE Pattern_Folder `06_recursion_and_backtracking` SHALL contain Sub_Pattern_Folders: `subsets_permutations`, `constraint_based`
7. THE Pattern_Folder `07_dynamic_programming` SHALL contain Sub_Pattern_Folders: `memoization`, `bottom_up`, `dp_on_trees`
8. THE Util_Folder SHALL remain at `src/util/` with all existing shared classes preserved

### Requirement 2: Problem Classification and Migration

**User Story:** As a developer studying for interviews, I want each problem placed in the correct pattern folder, so that I can find problems by the technique they teach.

#### Acceptance Criteria

1. WHEN a Problem_Folder is migrated, THE Repository SHALL place the Problem_Folder inside the appropriate Pattern_Folder or Sub_Pattern_Folder based on the primary algorithmic technique used in the solution
2. WHEN a problem uses multiple techniques, THE Repository SHALL place the Problem_Folder in the folder matching the primary technique
3. THE Repository SHALL migrate all existing Problem_Folders from the current flat structure into the new pattern-based structure
4. WHEN a Problem_Folder is migrated, THE Repository SHALL preserve the original `README.md` and all `.java` solution files without modification to their content (aside from metadata tag additions defined in Requirement 3)
5. IF a Problem_Folder cannot be classified into an existing Pattern_Folder, THEN THE Repository SHALL place the Problem_Folder in the most closely related Pattern_Folder

### Requirement 3: Problem README Metadata Tags

**User Story:** As a developer studying for interviews, I want each problem tagged with difficulty and company origin, so that I can filter problems by difficulty level and see which companies ask them.

#### Acceptance Criteria

1. WHEN a Problem_Folder is migrated, THE Problem_README SHALL include a Difficulty_Tag with one of the values: Easy, Medium, Hard
2. WHEN a Problem_Folder is migrated, THE Problem_README SHALL include a link to the original problem on LeetCode or the relevant source
3. WHEN a Problem_Folder originated from a company-specific folder (e.g., `facebook_practice/`, `doordash/`, `goldman_sachs/`), THE Problem_README SHALL include a Company_Tag indicating the originating company
4. THE Problem_README SHALL display metadata tags in a consistent format at the top of the file using a metadata header block

### Requirement 4: Pattern Documentation

**User Story:** As a developer studying for interviews, I want each pattern folder to include an explanation of the technique, so that I can learn the pattern before solving problems.

#### Acceptance Criteria

1. THE Repository SHALL include a PATTERN_MD file in each top-level Pattern_Folder
2. THE PATTERN_MD SHALL contain a description of the algorithmic pattern or data structure
3. THE PATTERN_MD SHALL contain guidance on when to apply the pattern (common problem signals)
4. THE PATTERN_MD SHALL contain the general time and space complexity characteristics of the pattern
5. WHEN a Pattern_Folder contains Sub_Pattern_Folders, THE PATTERN_MD SHALL describe each sub-pattern and how the sub-patterns relate to the parent pattern

### Requirement 5: Company Index

**User Story:** As a developer preparing for a specific company interview, I want a company-to-problem mapping, so that I can quickly find problems asked by a target company.

#### Acceptance Criteria

1. THE Repository SHALL include a Company_Index file at the root level named `COMPANY_INDEX.md`
2. THE Company_Index SHALL list each company that appears as a Company_Tag in the repository
3. WHEN a company is listed, THE Company_Index SHALL include links to each Problem_Folder associated with that company
4. THE Company_Index SHALL organize companies in alphabetical order

### Requirement 6: Root README Table of Contents

**User Story:** As a developer studying for interviews, I want a root README that serves as a table of contents with a recommended study order, so that I can follow a structured learning path.

#### Acceptance Criteria

1. THE Root_README SHALL contain a table of contents listing all Pattern_Folders in their numbered order
2. THE Root_README SHALL list each problem within each Pattern_Folder as a linked entry
3. THE Root_README SHALL include a recommended study order that follows the numbered Pattern_Folder sequence (01 through 12)
4. THE Root_README SHALL include a progress tracker using markdown checkboxes for each problem
5. THE Root_README SHALL include a link to the Company_Index
6. THE Root_README SHALL include a brief description of the repository purpose and how to use the study guide

### Requirement 7: Problems JSON Index

**User Story:** As a developer building a frontend on top of this repository, I want a machine-readable JSON index of all problems, so that a static site can consume the data without parsing the file system.

#### Acceptance Criteria

1. THE Repository SHALL include a Problems_JSON file at the root level named `problems.json`
2. THE Problems_JSON SHALL contain an array of objects, one per problem, each including: `name`, `pattern`, `subPattern` (if applicable), `difficulty`, `companies` (array), `path` (relative folder path), `sourceUrl` (link to original problem)
3. THE Problems_JSON SHALL be regenerated whenever a problem is added, moved, or updated
4. THE Problems_JSON SHALL be valid JSON that can be consumed by a static frontend without transformation

### Requirement 8: Java Import Path Consistency

**User Story:** As a developer, I want all Java files to compile after reorganization, so that I can run and test solutions without fixing broken imports.

#### Acceptance Criteria

1. WHEN a Problem_Folder is migrated to a new location, THE Repository SHALL update Java package declarations in all `.java` files to match the new directory path
2. WHEN a `.java` file imports a class from the Util_Folder, THE Repository SHALL update the import statement to reference the correct path relative to the new structure
3. IF a `.java` file references another `.java` file that has moved, THEN THE Repository SHALL update the reference to the new location

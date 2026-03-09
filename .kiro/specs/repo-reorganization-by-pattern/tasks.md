# Implementation Plan: Repo Reorganization by Pattern

## Overview

Reorganize a Java coding interview practice repository (~70 problems) from a flat company/problem-name structure into 12 numbered pattern-based folders. The migration pipeline: build classification map → create directories → move problems via git mv → update Java packages → inject README metadata → generate documentation files (PATTERN.md, COMPANY_INDEX.md, root README, problems.json) → verify compilation.

## Tasks

- [x] 1. Build the classification map
  - [x] 1.1 Create `classification.json` at the repo root containing an entry for every problem folder under `src/`
    - Each entry maps `sourcePath` → `{ pattern, subPattern, difficulty, companies, sourceUrl }`
    - Enumerate all problem folders (skip `util/`, `tree/`, `package_layout/`; recurse into company folders like `facebook_practice/`, `doordash/`, `goldman_sachs/`, `microsoft_preparation/`, `palantir/`, `amazon_oa_2020_Jul/`, `twitter_coding_challenge/`, `warner_bros_discovery/`, `geli_take_home/`)
    - Classify each problem by its primary algorithmic technique into one of the 12 pattern folders and optional sub-pattern
    - Assign difficulty (Easy/Medium/Hard) and LeetCode source URL for each problem
    - Populate `companies` array from the originating company folder name
    - _Requirements: 2.1, 2.2, 2.3, 2.5, 3.1, 3.2, 3.3_

  - [ ]* 1.2 Write property test: classification map covers all problems
    - **Property 1: Complete Migration (data completeness check)**
    - Verify every problem folder in the current `src/` has a corresponding entry in `classification.json`
    - **Validates: Requirements 2.1, 2.3**

- [x] 2. Create target directory structure and migrate problem folders
  - [x] 2.1 Create the 12 numbered pattern folders and all sub-pattern folders under `src/`
    - Create: `01_arrays_and_strings/{two_pointers,sliding_window,prefix_sum}`, `02_linked_lists/`, `03_stacks_and_queues/{monotonic_stack,basic}`, `04_trees/{dfs,bfs,construction}`, `05_graphs/{dfs_bfs,topological_sort,union_find,shortest_path}`, `06_recursion_and_backtracking/{subsets_permutations,constraint_based}`, `07_dynamic_programming/{memoization,bottom_up,dp_on_trees}`, `08_binary_search/`, `09_heaps_and_priority_queues/`, `10_tries/`, `11_design/`, `12_greedy/`
    - Verify `src/util/` is untouched
    - _Requirements: 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8_

  - [x] 2.2 Write the migration script (`migrate.sh`) that reads `classification.json` and moves each problem folder via `git mv`
    - For each entry: `git mv src/<sourcePath> src/<pattern>/<subPattern?>/<problemName>/`
    - Handle company-nested folders (e.g., `facebook_practice/three_sum` → move the inner problem folder, not the company wrapper)
    - After all moves, remove empty company wrapper directories
    - Log each move for audit
    - _Requirements: 2.1, 2.3_

  - [x] 2.3 Run the migration script to move all problem folders
    - Execute `migrate.sh`
    - Verify no problem folders remain in old flat/company locations
    - _Requirements: 2.1, 2.3_

  - [ ]* 2.4 Write property test: all problems placed in valid pattern folders
    - **Property 2: Valid Placement**
    - For each problem folder in the new structure, verify its parent is a valid pattern/sub-pattern path from Requirement 1
    - **Validates: Requirements 2.1**

- [x] 3. Update Java package declarations and imports
  - [x] 3.1 Write a script to update `package` declarations in all `.java` files to match their new directory path relative to `src/`
    - For each `.java` file under `src/` (excluding `util/`), replace the `package` line with the correct package derived from the file's directory path
    - If a `.java` file has no `package` declaration, insert one
    - _Requirements: 8.1_

  - [x] 3.2 Run the package update script and verify imports still resolve
    - Since `util/` doesn't move, `import util.*` statements remain valid
    - Verify no cross-problem imports exist that need updating
    - _Requirements: 8.1, 8.2, 8.3_

  - [ ]* 3.3 Write property test: package declarations match directory paths
    - **Property 12: Package Declaration Matches Directory Path**
    - For every `.java` file under `src/`, extract the `package` declaration and verify it equals the directory path relative to `src/` with `/` replaced by `.`
    - **Validates: Requirements 8.1**

  - [ ]* 3.4 Write property test: all imports resolve
    - **Property 13: All Imports Resolve**
    - For every `import` statement in every `.java` file, verify the target is a `java.*`/`javax.*` standard library import or corresponds to a valid class in `src/util/` or another problem folder
    - **Validates: Requirements 8.2, 8.3**

- [x] 4. Checkpoint - Verify migration integrity
  - Ensure all problem folders have been moved, Java files compile, and no files remain in old locations. Ask the user if questions arise.

- [x] 5. Inject README metadata tags
  - [x] 5.1 Write a script that reads `classification.json` and injects a metadata header table into each problem's `README.md`
    - Insert a table with Difficulty, Companies, and Source columns at the top of each README (after the `# Problem Name` heading)
    - Preserve all existing README content below the injected metadata block
    - For problems from company folders, include the Company_Tag
    - For problems without a known LeetCode URL, use `TBD` as placeholder
    - _Requirements: 3.1, 3.2, 3.3, 3.4_

  - [x] 5.2 Run the metadata injection script across all problem READMEs
    - _Requirements: 3.1, 3.2, 3.3, 3.4_

  - [ ]* 5.3 Write property test: README metadata completeness
    - **Property 4: README Metadata Completeness**
    - For every problem README, verify it contains a metadata header block with a valid difficulty tag (Easy/Medium/Hard) and a source URL
    - **Validates: Requirements 3.1, 3.2, 3.4**

  - [ ]* 5.4 Write property test: company tag presence for company-originated problems
    - **Property 5: Company Tag Presence for Company-Originated Problems**
    - For every problem that originated from a company-specific folder, verify its README includes the company tag
    - **Validates: Requirements 3.3**

  - [ ]* 5.5 Write property test: content preservation
    - **Property 3: Content Preservation**
    - For every migrated problem, verify Java file content (excluding `package` line) is identical to the original, and README content (excluding injected metadata header) is identical to the original
    - Requires storing pre-migration snapshots before running migration
    - **Validates: Requirements 2.4**

- [x] 6. Generate PATTERN.md files
  - [x] 6.1 Write a script or create PATTERN.md files for each of the 12 pattern folders
    - Each PATTERN.md must include: description of the pattern, when to apply it (common problem signals), time/space complexity characteristics
    - For pattern folders with sub-patterns, include a sub-patterns section describing each sub-pattern and its relationship to the parent
    - _Requirements: 4.1, 4.2, 4.3, 4.4, 4.5_

  - [ ]* 6.2 Write property test: PATTERN.md required sections
    - **Property 6: PATTERN.md Required Sections**
    - For every PATTERN.md, verify it contains description, when-to-apply, and complexity sections
    - **Validates: Requirements 4.2, 4.3, 4.4**

  - [ ]* 6.3 Write property test: PATTERN.md sub-pattern coverage
    - **Property 7: PATTERN.md Sub-Pattern Coverage**
    - For every pattern folder that has sub-pattern folders, verify its PATTERN.md mentions each sub-pattern by name
    - **Validates: Requirements 4.5**

- [-] 7. Generate COMPANY_INDEX.md
  - [-] 7.1 Write a script that reads `classification.json`, groups problems by company, and generates `COMPANY_INDEX.md` at the repo root
    - List companies in alphabetical order
    - Under each company heading, link to every associated problem folder
    - _Requirements: 5.1, 5.2, 5.3, 5.4_

  - [ ]* 7.2 Write property test: company index completeness
    - **Property 8: Company Index Completeness**
    - Collect all company tags from problem READMEs, verify each company+problem pair appears in COMPANY_INDEX.md
    - **Validates: Requirements 5.2, 5.3**

  - [ ]* 7.3 Write property test: company index alphabetical order
    - **Property 9: Company Index Alphabetical Order**
    - Extract company headings from COMPANY_INDEX.md, verify they are in sorted order
    - **Validates: Requirements 5.4**

- [ ] 8. Generate root README
  - [ ] 8.1 Generate the root `README.md` with table of contents, study order, and progress tracker
    - Include repository description and usage guide
    - List all 12 pattern folders in numbered order (01-12)
    - Under each pattern, list every problem as a markdown checkbox link (`- [ ] [problem_name](path)`)
    - Include recommended study order following the 01-12 sequence
    - Include link to COMPANY_INDEX.md
    - _Requirements: 6.1, 6.2, 6.3, 6.4, 6.5, 6.6_

  - [ ]* 8.2 Write property test: root README problem coverage
    - **Property 10: Root README Problem Coverage**
    - For every problem folder in the repo, verify a checkbox link exists in the root README
    - **Validates: Requirements 6.2, 6.4**

- [ ] 9. Generate problems.json
  - [ ] 9.1 Write a script that reads `classification.json` and generates `problems.json` at the repo root
    - Output a JSON array with one object per problem: `name`, `pattern`, `subPattern`, `difficulty`, `companies`, `path`, `sourceUrl`
    - Validate the output is valid JSON
    - _Requirements: 7.1, 7.2, 7.4_

  - [ ]* 9.2 Write property test: problems.json completeness and validity
    - **Property 11: problems.json Completeness and Validity**
    - Parse `problems.json`, verify each entry has all required fields, verify `path` corresponds to an actual directory, verify total count matches problem folder count
    - **Validates: Requirements 7.2, 7.4**

- [ ] 10. Final checkpoint - Full verification
  - Run `javac` compilation check on all `.java` files under `src/` to confirm no broken packages or imports
  - Verify all generated files exist (12 PATTERN.md files, COMPANY_INDEX.md, README.md, problems.json)
  - Ensure all tests pass, ask the user if questions arise.

## Notes

- Tasks marked with `*` are optional and can be skipped for faster MVP
- Each task references specific requirements for traceability
- Property tests validate universal correctness properties from the design document
- The classification map (`classification.json`) is the single source of truth driving all downstream generation
- `src/util/` is never moved — only importing files change their package declarations
- Checkpoints at tasks 4 and 10 ensure incremental validation

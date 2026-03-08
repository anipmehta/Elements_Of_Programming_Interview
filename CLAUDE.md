# CLAUDE.md

## Branching

- Always create a new branch for each feature or change. Never commit directly to `main`.
- Branch naming: `feature/<short-description>` for features, `fix/<short-description>` for fixes.
  - Example: `feature/add-tree-dfs-problems`, `fix/broken-import-paths`

## Commits

- Write clear, descriptive commit messages.
- Format: `<type>: <short summary>` where type is `feat`, `fix`, `refactor`, `docs`, `chore`.
  - Example: `feat: add binary search pattern folder and problems`
  - Example: `docs: update root README with progress tracker`
- Keep commits focused — one logical change per commit.

## Code Quality

- Make sure all Java files compile before committing. Do not commit broken code.
- Verify import paths are correct after moving or adding files.
- Run any existing tests before pushing.

## Project Structure

- Every problem folder must contain a `README.md` and at least one `Solution.java`.
- Shared utilities go in `src/util/`.
- Follow the numbered pattern-based folder structure under `src/`.

#!/usr/bin/env python3
"""Inject metadata header tables into problem README.md files using classification.json."""

import json
import os
import re
import sys

def load_classification():
    with open("classification.json", "r") as f:
        return json.load(f)

def find_readme_path(problem_name, classification):
    """Find the actual README.md path for a problem based on its classification."""
    entry = classification[problem_name]
    pattern = entry["pattern"]
    sub = entry["subPattern"]

    # Derive the folder name (last segment of the source path)
    folder_name = problem_name.split("/")[-1]

    # Handle target_sum collision: microsoft's target_sum was renamed to target_sum_msft
    if problem_name == "microsoft_preparation/target_sum":
        folder_name = "target_sum_msft"

    if sub:
        path = os.path.join("src", pattern, sub, folder_name, "README.md")
    else:
        path = os.path.join("src", pattern, folder_name, "README.md")

    return path

def build_metadata_block(entry):
    """Build the metadata table string."""
    difficulty = entry["difficulty"]
    companies = entry["companies"]
    source_url = entry["sourceUrl"]

    company_str = ", ".join(companies) if companies else "—"
    source_str = f"[LeetCode]({source_url})" if source_url and source_url != "TBD" else "TBD"

    lines = [
        "",
        "| | |",
        "|---|---|",
        f"| **Difficulty** | {difficulty} |",
        f"| **Companies** | {company_str} |",
        f"| **Source** | {source_str} |",
        "",
    ]
    return "\n".join(lines)

METADATA_MARKER = "| **Difficulty**"

def inject_metadata(readme_path, metadata_block):
    """Inject metadata block into README, after the first heading. Skip if already injected."""
    if not os.path.exists(readme_path):
        # Create a minimal README with just the metadata
        folder_name = os.path.basename(os.path.dirname(readme_path))
        title = folder_name.replace("_", " ").title()
        content = f"## {title}\n{metadata_block}\n"
        os.makedirs(os.path.dirname(readme_path), exist_ok=True)
        with open(readme_path, "w") as f:
            f.write(content)
        return "created"

    with open(readme_path, "r") as f:
        content = f.read()

    # Skip if already has metadata
    if METADATA_MARKER in content:
        return "skipped"

    lines = content.split("\n")

    # Find the first heading line (## or #)
    insert_idx = None
    for i, line in enumerate(lines):
        if line.startswith("#"):
            insert_idx = i + 1
            break

    if insert_idx is None:
        # No heading found, insert at top
        insert_idx = 0

    # Insert metadata block after the heading
    meta_lines = metadata_block.split("\n")
    new_lines = lines[:insert_idx] + meta_lines + lines[insert_idx:]
    new_content = "\n".join(new_lines)

    with open(readme_path, "w") as f:
        f.write(new_content)
    return "injected"


def main():
    classification = load_classification()
    stats = {"injected": 0, "skipped": 0, "created": 0, "missing": 0}

    for problem_name, entry in sorted(classification.items()):
        readme_path = find_readme_path(problem_name, classification)
        metadata_block = build_metadata_block(entry)

        result = inject_metadata(readme_path, metadata_block)
        stats[result] += 1
        print(f"  {result.upper():8s} {readme_path}")

    print(f"\nDone! Injected: {stats['injected']}, Created: {stats['created']}, "
          f"Skipped: {stats['skipped']}, Missing: {stats['missing']}")

if __name__ == "__main__":
    main()

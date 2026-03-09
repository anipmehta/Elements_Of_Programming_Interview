#!/usr/bin/env python3
"""Generate COMPANY_INDEX.md, root README.md, and problems.json from classification.json."""

import json
import os
from collections import defaultdict

def load_classification():
    with open("classification.json", "r") as f:
        return json.load(f)

def resolve_path(problem_name, entry):
    """Resolve the actual filesystem path for a problem."""
    pattern = entry["pattern"]
    sub = entry["subPattern"]
    folder = problem_name.split("/")[-1]
    if problem_name == "microsoft_preparation/target_sum":
        folder = "target_sum_msft"
    if sub:
        return f"src/{pattern}/{sub}/{folder}"
    return f"src/{pattern}/{folder}"

# ── COMPANY_INDEX.md ──

def generate_company_index(classification):
    company_map = defaultdict(list)
    for name, entry in classification.items():
        for company in entry.get("companies", []):
            path = resolve_path(name, entry)
            folder = name.split("/")[-1]
            if name == "microsoft_preparation/target_sum":
                folder = "target_sum_msft"
            display = folder.replace("_", " ").title()
            company_map[company].append((display, path, entry["difficulty"]))

    lines = ["# Company Index", "",
             "Problems grouped by company. Each link points to the problem folder.", ""]

    for company in sorted(company_map.keys()):
        lines.append(f"## {company}")
        lines.append("")
        lines.append("| Problem | Difficulty | Path |")
        lines.append("|---|---|---|")
        for display, path, diff in sorted(company_map[company], key=lambda x: x[0]):
            lines.append(f"| [{display}]({path}) | {diff} | `{path}` |")
        lines.append("")

    with open("COMPANY_INDEX.md", "w") as f:
        f.write("\n".join(lines))
    print(f"Generated COMPANY_INDEX.md ({len(company_map)} companies)")


# ── ROOT README.md ──

PATTERN_NAMES = {
    "01_arrays_and_strings": "Arrays & Strings",
    "02_linked_lists": "Linked Lists",
    "03_stacks_and_queues": "Stacks & Queues",
    "04_trees": "Trees",
    "05_graphs": "Graphs",
    "06_recursion_and_backtracking": "Recursion & Backtracking",
    "07_dynamic_programming": "Dynamic Programming",
    "08_binary_search": "Binary Search",
    "09_heaps_and_priority_queues": "Heaps & Priority Queues",
    "10_tries": "Tries",
    "11_design": "Design",
    "12_greedy": "Greedy",
}

def generate_readme(classification):
    # Group problems by pattern
    pattern_problems = defaultdict(list)
    for name, entry in classification.items():
        path = resolve_path(name, entry)
        folder = name.split("/")[-1]
        if name == "microsoft_preparation/target_sum":
            folder = "target_sum_msft"
        display = folder.replace("_", " ").replace("msft", "(Microsoft)").title()
        pattern_problems[entry["pattern"]].append((display, path, entry["difficulty"], entry.get("subPattern")))

    lines = [
        "# Elements of Programming Interview — Pattern-Based Study Guide",
        "",
        "A collection of ~115 coding interview problems organized by algorithmic pattern.",
        "Work through patterns 01→12 for a structured study path.",
        "",
        "## Quick Links",
        "",
        "- [Company Index](COMPANY_INDEX.md) — problems grouped by company",
        "- [Problems JSON](problems.json) — machine-readable problem index",
        "",
        "## Study Order",
        "",
    ]

    total = 0
    for pattern_key in sorted(PATTERN_NAMES.keys()):
        display_name = PATTERN_NAMES[pattern_key]
        problems = sorted(pattern_problems.get(pattern_key, []), key=lambda x: x[0])
        count = len(problems)
        total += count
        lines.append(f"### {pattern_key.split('_', 1)[0]}. [{display_name}](src/{pattern_key}/PATTERN.md) ({count} problems)")
        lines.append("")

        # Group by sub-pattern
        no_sub = [p for p in problems if not p[3]]
        with_sub = defaultdict(list)
        for p in problems:
            if p[3]:
                with_sub[p[3]].append(p)

        for p in no_sub:
            lines.append(f"- [ ] [{p[0]}]({p[1]}) — {p[2]}")

        for sub_key in sorted(with_sub.keys()):
            sub_display = sub_key.replace("_", " ").title()
            lines.append(f"- **{sub_display}**")
            for p in sorted(with_sub[sub_key], key=lambda x: x[0]):
                lines.append(f"  - [ ] [{p[0]}]({p[1]}) — {p[2]}")

        lines.append("")

    lines.append(f"---")
    lines.append(f"")
    lines.append(f"**Total: {total} problems**")
    lines.append("")

    with open("README.md", "w") as f:
        f.write("\n".join(lines))
    print(f"Generated README.md ({total} problems across {len(PATTERN_NAMES)} patterns)")


# ── problems.json ──

def generate_problems_json(classification):
    problems = []
    for name, entry in sorted(classification.items()):
        path = resolve_path(name, entry)
        folder = name.split("/")[-1]
        if name == "microsoft_preparation/target_sum":
            folder = "target_sum_msft"
        problems.append({
            "name": folder,
            "pattern": entry["pattern"],
            "subPattern": entry["subPattern"],
            "difficulty": entry["difficulty"],
            "companies": entry["companies"],
            "path": path,
            "sourceUrl": entry["sourceUrl"],
        })

    with open("problems.json", "w") as f:
        json.dump(problems, f, indent=2)
    print(f"Generated problems.json ({len(problems)} entries)")

# ── Main ──

def main():
    classification = load_classification()
    generate_company_index(classification)
    generate_readme(classification)
    generate_problems_json(classification)

if __name__ == "__main__":
    main()

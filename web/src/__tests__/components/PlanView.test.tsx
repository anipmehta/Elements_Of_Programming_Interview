import { describe, it, expect, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import PlanView from "../../components/PlanView";
import type { StudyPlan } from "../../lib/studyPlanTypes";
import type { ProblemEntry } from "../../lib/types";

const mockProblems: ProblemEntry[] = [
  {
    name: "three_sum",
    pattern: "01_arrays_and_strings",
    subPattern: "two_pointers",
    difficulty: "Medium",
    companies: ["Google"],
    path: "src/01_arrays_and_strings/three_sum",
    sourceUrl: "https://leetcode.com/problems/3sum/",
  },
  {
    name: "is_valid_bst",
    pattern: "04_trees",
    subPattern: "dfs",
    difficulty: "Medium",
    companies: ["Amazon"],
    path: "src/04_trees/is_valid_bst",
    sourceUrl: "https://leetcode.com/problems/validate-binary-search-tree/",
  },
];

const mockPlan: StudyPlan = {
  id: "test-plan",
  name: "Test Plan",
  description: "Test",
  author: "test",
  tags: ["test"],
  sections: [
    { order: 1, focus: "Arrays", problemNames: ["three_sum"] },
    { order: 2, focus: "Trees", problemNames: ["is_valid_bst"] },
  ],
};

const defaultProps = {
  plan: mockPlan,
  problems: mockProblems,
  completed: new Set<string>(),
  onToggle: vi.fn(),
};

describe("PlanView", () => {
  it("renders progress bar with correct text when nothing is completed", () => {
    render(<PlanView {...defaultProps} />);

    expect(screen.getByText("0 / 2 completed (0%)")).toBeInTheDocument();
  });

  it("renders sections in order with focus descriptions", () => {
    const { container } = render(<PlanView {...defaultProps} />);

    const sectionItems = container.querySelectorAll(".plan-section");
    expect(sectionItems).toHaveLength(2);

    expect(screen.getByText("Section 1")).toBeInTheDocument();
    expect(screen.getByText("Section 2")).toBeInTheDocument();

    // Focus descriptions rendered as " — {focus}"
    const focusElements = container.querySelectorAll(".plan-section-focus");
    expect(focusElements).toHaveLength(2);
    expect(focusElements[0].textContent).toContain("Arrays");
    expect(focusElements[1].textContent).toContain("Trees");
  });

  it("renders problem names and difficulty badges", () => {
    render(<PlanView {...defaultProps} />);

    expect(screen.getByText("three_sum")).toBeInTheDocument();
    expect(screen.getByText("is_valid_bst")).toBeInTheDocument();
    // Both problems are Medium difficulty
    expect(screen.getAllByText("Medium")).toHaveLength(2);
  });

  it("shows section completion indicator when all problems in section are done", () => {
    const completed = new Set(["three_sum"]);
    render(<PlanView {...defaultProps} completed={completed} />);

    // Section 1 has only three_sum, which is completed
    expect(screen.getByLabelText("Section complete")).toBeInTheDocument();
  });

  it("does not show section completion indicator when section is incomplete", () => {
    render(<PlanView {...defaultProps} completed={new Set<string>()} />);

    expect(screen.queryByLabelText("Section complete")).not.toBeInTheDocument();
  });

  it("calls onToggle when checkbox is clicked", async () => {
    const onToggle = vi.fn();
    render(<PlanView {...defaultProps} onToggle={onToggle} />);

    const checkbox = screen.getByLabelText("Mark three_sum as complete");
    await userEvent.click(checkbox);

    expect(onToggle).toHaveBeenCalledWith("three_sum");
  });

  it("shows source link for problems with sourceUrl", () => {
    render(<PlanView {...defaultProps} />);

    const links = screen.getAllByRole("link", { name: "Source" });
    expect(links).toHaveLength(2);
    expect(links[0]).toHaveAttribute(
      "href",
      "https://leetcode.com/problems/3sum/",
    );
    expect(links[1]).toHaveAttribute(
      "href",
      "https://leetcode.com/problems/validate-binary-search-tree/",
    );
  });

  it("updates progress when completed set changes", () => {
    const completed = new Set(["three_sum", "is_valid_bst"]);
    render(<PlanView {...defaultProps} completed={completed} />);

    expect(screen.getByText("2 / 2 completed (100%)")).toBeInTheDocument();
  });

  it("links pattern name to PATTERN.md on GitHub", () => {
    render(<PlanView {...defaultProps} />);

    const patternLink = screen.getByRole("link", { name: "Arrays & Strings" });
    expect(patternLink).toHaveAttribute(
      "href",
      "https://github.com/anipmehta/AlgoForge/blob/master/src/01_arrays_and_strings/PATTERN.md",
    );
  });
});

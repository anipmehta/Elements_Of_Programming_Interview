import { describe, it, expect, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import ProblemTable from "../../components/ProblemTable";
import type { ProblemEntry } from "../../lib/types";

function makeProblem(overrides: Partial<ProblemEntry> = {}): ProblemEntry {
  return {
    name: "test_problem",
    pattern: "01_arrays_and_strings",
    subPattern: null,
    difficulty: "Medium",
    companies: [],
    path: "src/01_arrays_and_strings/test_problem",
    sourceUrl: "https://leetcode.com/problems/test",
    ...overrides,
  };
}

const defaultProps = {
  completed: new Set<string>(),
  onToggle: vi.fn(),
};

describe("ProblemTable", () => {
  it("renders table headers including checkbox column", () => {
    render(<ProblemTable problems={[]} {...defaultProps} />);
    expect(screen.getByText("✓")).toBeInTheDocument();
    expect(screen.getByText("Name")).toBeInTheDocument();
    expect(screen.getByText("Pattern")).toBeInTheDocument();
    expect(screen.getByText("Sub-Pattern")).toBeInTheDocument();
    expect(screen.getByText("Difficulty")).toBeInTheDocument();
    expect(screen.getByText("Companies")).toBeInTheDocument();
  });

  it("renders problem name as link when sourceUrl is not TBD", () => {
    const problem = makeProblem({ name: "two_sum", sourceUrl: "https://leetcode.com/problems/two-sum" });
    render(<ProblemTable problems={[problem]} {...defaultProps} />);
    const link = screen.getByText("two_sum");
    expect(link.tagName).toBe("A");
    expect(link).toHaveAttribute("href", "https://leetcode.com/problems/two-sum");
    expect(link).toHaveAttribute("target", "_blank");
    expect(link).toHaveAttribute("rel", "noopener noreferrer");
  });

  it("renders problem name as plain text when sourceUrl is TBD", () => {
    const problem = makeProblem({ name: "custom_problem", sourceUrl: "TBD" });
    render(<ProblemTable problems={[problem]} {...defaultProps} />);
    const text = screen.getByText("custom_problem");
    expect(text.tagName).not.toBe("A");
  });

  it("renders solution link for each problem", () => {
    const problem = makeProblem({ path: "src/01_arrays_and_strings/two_sum" });
    render(<ProblemTable problems={[problem]} {...defaultProps} />);
    const solutionLink = screen.getByTitle("View solution on GitHub");
    expect(solutionLink).toHaveAttribute(
      "href",
      "https://github.com/anipmehta/Elements_Of_Programming_Interview/tree/master/src/01_arrays_and_strings/two_sum"
    );
    expect(solutionLink).toHaveAttribute("target", "_blank");
  });

  it("renders dash for null subPattern", () => {
    const problem = makeProblem({ subPattern: null });
    render(<ProblemTable problems={[problem]} {...defaultProps} />);
    const dashes = screen.getAllByText("—");
    expect(dashes.length).toBeGreaterThanOrEqual(1);
  });

  it("renders subPattern when present", () => {
    const problem = makeProblem({ subPattern: "sliding_window" });
    render(<ProblemTable problems={[problem]} {...defaultProps} />);
    expect(screen.getByText("sliding_window")).toBeInTheDocument();
  });

  it("renders companies joined by comma", () => {
    const problem = makeProblem({ companies: ["Google", "Amazon"] });
    render(<ProblemTable problems={[problem]} {...defaultProps} />);
    expect(screen.getByText("Google, Amazon")).toBeInTheDocument();
  });

  it("renders dash when companies array is empty", () => {
    const problem = makeProblem({ companies: [] });
    render(<ProblemTable problems={[problem]} {...defaultProps} />);
    const dashes = screen.getAllByText("—");
    expect(dashes.length).toBeGreaterThanOrEqual(1);
  });

  it("formats pattern by stripping prefix and capitalizing", () => {
    const problem = makeProblem({ pattern: "04_trees" });
    render(<ProblemTable problems={[problem]} {...defaultProps} />);
    expect(screen.getByText("Trees")).toBeInTheDocument();
  });

  it("renders empty tbody when no problems", () => {
    const { container } = render(<ProblemTable problems={[]} {...defaultProps} />);
    const rows = container.querySelectorAll("tbody tr");
    expect(rows).toHaveLength(0);
  });

  // Checkbox tests
  it("renders unchecked checkbox for incomplete problem", () => {
    const problem = makeProblem({ name: "two_sum" });
    render(<ProblemTable problems={[problem]} {...defaultProps} />);
    const checkbox = screen.getByRole("checkbox");
    expect(checkbox).not.toBeChecked();
  });

  it("renders checked checkbox for completed problem", () => {
    const problem = makeProblem({ name: "two_sum" });
    render(<ProblemTable problems={[problem]} completed={new Set(["two_sum"])} onToggle={vi.fn()} />);
    const checkbox = screen.getByRole("checkbox");
    expect(checkbox).toBeChecked();
  });

  it("calls onToggle when checkbox is clicked", async () => {
    const problem = makeProblem({ name: "two_sum" });
    const onToggle = vi.fn();
    render(<ProblemTable problems={[problem]} completed={new Set()} onToggle={onToggle} />);
    await userEvent.click(screen.getByRole("checkbox"));
    expect(onToggle).toHaveBeenCalledWith("two_sum");
  });

  it("applies completed-row class for completed problems", () => {
    const problem = makeProblem({ name: "two_sum" });
    const { container } = render(
      <ProblemTable problems={[problem]} completed={new Set(["two_sum"])} onToggle={vi.fn()} />
    );
    const row = container.querySelector("tbody tr");
    expect(row).toHaveClass("completed-row");
  });

  it("does not apply completed-row class for incomplete problems", () => {
    const problem = makeProblem({ name: "two_sum" });
    const { container } = render(
      <ProblemTable problems={[problem]} completed={new Set()} onToggle={vi.fn()} />
    );
    const row = container.querySelector("tbody tr");
    expect(row).not.toHaveClass("completed-row");
  });
});

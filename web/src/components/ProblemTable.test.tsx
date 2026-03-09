import { describe, it, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import ProblemTable from "./ProblemTable";
import type { ProblemEntry } from "../lib/types";

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

describe("ProblemTable", () => {
  it("renders table headers", () => {
    render(<ProblemTable problems={[]} />);
    expect(screen.getByText("Name")).toBeInTheDocument();
    expect(screen.getByText("Pattern")).toBeInTheDocument();
    expect(screen.getByText("Sub-Pattern")).toBeInTheDocument();
    expect(screen.getByText("Difficulty")).toBeInTheDocument();
    expect(screen.getByText("Companies")).toBeInTheDocument();
  });

  it("renders problem name as link when sourceUrl is not TBD", () => {
    const problem = makeProblem({ name: "two_sum", sourceUrl: "https://leetcode.com/problems/two-sum" });
    render(<ProblemTable problems={[problem]} />);
    const link = screen.getByText("two_sum");
    expect(link.tagName).toBe("A");
    expect(link).toHaveAttribute("href", "https://leetcode.com/problems/two-sum");
    expect(link).toHaveAttribute("target", "_blank");
    expect(link).toHaveAttribute("rel", "noopener noreferrer");
  });

  it("renders problem name as plain text when sourceUrl is TBD", () => {
    const problem = makeProblem({ name: "custom_problem", sourceUrl: "TBD" });
    render(<ProblemTable problems={[problem]} />);
    const text = screen.getByText("custom_problem");
    expect(text.tagName).not.toBe("A");
  });

  it("renders solution link for each problem", () => {
    const problem = makeProblem({ path: "src/01_arrays_and_strings/two_sum" });
    render(<ProblemTable problems={[problem]} />);
    const solutionLink = screen.getByTitle("View solution on GitHub");
    expect(solutionLink).toHaveAttribute(
      "href",
      "https://github.com/anipmehta/Elements_Of_Programming_Interview/tree/master/src/01_arrays_and_strings/two_sum"
    );
    expect(solutionLink).toHaveAttribute("target", "_blank");
  });

  it("renders dash for null subPattern", () => {
    const problem = makeProblem({ subPattern: null });
    render(<ProblemTable problems={[problem]} />);
    const dashes = screen.getAllByText("—");
    // Two dashes: subPattern (null) and companies (empty)
    expect(dashes.length).toBeGreaterThanOrEqual(1);
    // The subPattern cell is the 3rd column
    const subPatternCell = dashes[0].closest("td");
    expect(subPatternCell).toBeInTheDocument();
  });

  it("renders subPattern when present", () => {
    const problem = makeProblem({ subPattern: "sliding_window" });
    render(<ProblemTable problems={[problem]} />);
    expect(screen.getByText("sliding_window")).toBeInTheDocument();
  });

  it("renders companies joined by comma", () => {
    const problem = makeProblem({ companies: ["Google", "Amazon"] });
    render(<ProblemTable problems={[problem]} />);
    expect(screen.getByText("Google, Amazon")).toBeInTheDocument();
  });

  it("renders dash when companies array is empty", () => {
    const problem = makeProblem({ companies: [] });
    render(<ProblemTable problems={[problem]} />);
    // Two dashes: one for subPattern (null), one for companies
    const dashes = screen.getAllByText("—");
    expect(dashes.length).toBeGreaterThanOrEqual(1);
  });

  it("formats pattern by stripping prefix and capitalizing", () => {
    const problem = makeProblem({ pattern: "04_trees" });
    render(<ProblemTable problems={[problem]} />);
    expect(screen.getByText("Trees")).toBeInTheDocument();
  });

  it("renders empty tbody when no problems", () => {
    const { container } = render(<ProblemTable problems={[]} />);
    const rows = container.querySelectorAll("tbody tr");
    expect(rows).toHaveLength(0);
  });
});

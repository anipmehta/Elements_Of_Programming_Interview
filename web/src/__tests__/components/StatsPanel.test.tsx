import { describe, it, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import StatsPanel from "../../components/StatsPanel";

const defaultProps = {
  overall: { total: 115, completed: 23, percentage: 20 },
  byDifficulty: [
    { label: "Easy", total: 12, completed: 8, percentage: 67 },
    { label: "Medium", total: 72, completed: 10, percentage: 14 },
    { label: "Hard", total: 31, completed: 5, percentage: 16 },
  ],
  byPattern: [
    { label: "arrays and strings", total: 18, completed: 5, percentage: 28 },
    { label: "linked lists", total: 10, completed: 3, percentage: 30 },
  ],
};

describe("StatsPanel", () => {
  it("renders overall progress", () => {
    render(<StatsPanel {...defaultProps} />);
    expect(screen.getByText("Progress: 23 / 115 (20%)")).toBeInTheDocument();
  });

  it("renders progress bar with correct aria attributes", () => {
    render(<StatsPanel {...defaultProps} />);
    const bars = screen.getAllByRole("progressbar");
    expect(bars[0]).toHaveAttribute("aria-valuenow", "20");
  });

  it("renders difficulty badges", () => {
    render(<StatsPanel {...defaultProps} />);
    expect(screen.getByText("Easy: 8/12")).toBeInTheDocument();
    expect(screen.getByText("Medium: 10/72")).toBeInTheDocument();
    expect(screen.getByText("Hard: 5/31")).toBeInTheDocument();
  });

  it("hides pattern breakdown by default", () => {
    render(<StatsPanel {...defaultProps} />);
    expect(screen.queryByText("arrays and strings")).not.toBeInTheDocument();
  });

  it("shows pattern breakdown when toggle is clicked", async () => {
    render(<StatsPanel {...defaultProps} />);
    await userEvent.click(screen.getByText("Show pattern breakdown"));
    expect(screen.getByText("arrays and strings")).toBeInTheDocument();
    expect(screen.getByText("5/18")).toBeInTheDocument();
    expect(screen.getByText("linked lists")).toBeInTheDocument();
    expect(screen.getByText("3/10")).toBeInTheDocument();
  });

  it("hides pattern breakdown when toggle is clicked again", async () => {
    render(<StatsPanel {...defaultProps} />);
    await userEvent.click(screen.getByText("Show pattern breakdown"));
    await userEvent.click(screen.getByText("Hide pattern breakdown"));
    expect(screen.queryByText("arrays and strings")).not.toBeInTheDocument();
  });

  it("renders zero state correctly", () => {
    const zeroProps = {
      overall: { total: 0, completed: 0, percentage: 0 },
      byDifficulty: [
        { label: "Easy", total: 0, completed: 0, percentage: 0 },
        { label: "Medium", total: 0, completed: 0, percentage: 0 },
        { label: "Hard", total: 0, completed: 0, percentage: 0 },
      ],
      byPattern: [],
    };
    render(<StatsPanel {...zeroProps} />);
    expect(screen.getByText("Progress: 0 / 0 (0%)")).toBeInTheDocument();
  });
});

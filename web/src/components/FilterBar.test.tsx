import { describe, it, expect, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import FilterBar from "./FilterBar";
import type { FilterState } from "../lib/types";

const defaultProps = {
  patterns: ["01_arrays_and_strings", "02_linked_lists"],
  difficulties: ["Easy", "Medium", "Hard"],
  companies: ["Amazon", "Google"],
  filters: { pattern: null, difficulty: null, company: null } as FilterState,
  onFilterChange: vi.fn(),
};

describe("FilterBar", () => {
  it("renders three dropdowns with labels", () => {
    render(<FilterBar {...defaultProps} />);
    expect(screen.getByLabelText("Pattern")).toBeInTheDocument();
    expect(screen.getByLabelText("Difficulty")).toBeInTheDocument();
    expect(screen.getByLabelText("Company")).toBeInTheDocument();
  });

  it("renders All as default option for each dropdown", () => {
    render(<FilterBar {...defaultProps} />);
    const selects = screen.getAllByRole("combobox");
    expect(selects).toHaveLength(3);
    selects.forEach((select) => {
      expect(select).toHaveValue("");
    });
  });

  it("renders pattern options with formatted names", () => {
    render(<FilterBar {...defaultProps} />);
    expect(screen.getByText("Arrays And Strings")).toBeInTheDocument();
    expect(screen.getByText("Linked Lists")).toBeInTheDocument();
  });

  it("calls onFilterChange with pattern when pattern is selected", async () => {
    const onFilterChange = vi.fn();
    render(<FilterBar {...defaultProps} onFilterChange={onFilterChange} />);
    const patternSelect = screen.getByLabelText("Pattern");
    await userEvent.selectOptions(patternSelect, "01_arrays_and_strings");
    expect(onFilterChange).toHaveBeenCalledWith({
      pattern: "01_arrays_and_strings",
      difficulty: null,
      company: null,
    });
  });

  it("calls onFilterChange with null when All is selected", async () => {
    const onFilterChange = vi.fn();
    const filters: FilterState = { pattern: "01_arrays_and_strings", difficulty: null, company: null };
    render(<FilterBar {...defaultProps} filters={filters} onFilterChange={onFilterChange} />);
    const patternSelect = screen.getByLabelText("Pattern");
    await userEvent.selectOptions(patternSelect, "");
    expect(onFilterChange).toHaveBeenCalledWith({
      pattern: null,
      difficulty: null,
      company: null,
    });
  });

  it("calls onFilterChange when difficulty is selected", async () => {
    const onFilterChange = vi.fn();
    render(<FilterBar {...defaultProps} onFilterChange={onFilterChange} />);
    await userEvent.selectOptions(screen.getByLabelText("Difficulty"), "Hard");
    expect(onFilterChange).toHaveBeenCalledWith({
      pattern: null,
      difficulty: "Hard",
      company: null,
    });
  });

  it("calls onFilterChange when company is selected", async () => {
    const onFilterChange = vi.fn();
    render(<FilterBar {...defaultProps} onFilterChange={onFilterChange} />);
    await userEvent.selectOptions(screen.getByLabelText("Company"), "Google");
    expect(onFilterChange).toHaveBeenCalledWith({
      pattern: null,
      difficulty: null,
      company: "Google",
    });
  });
});

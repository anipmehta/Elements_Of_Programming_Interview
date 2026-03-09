import { describe, it, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import StatusBar from "./StatusBar";

describe("StatusBar", () => {
  it("renders visible and total counts", () => {
    render(<StatusBar visibleCount={10} totalCount={115} />);
    expect(screen.getByText("Showing 10 of 115 problems")).toBeInTheDocument();
  });

  it("shows zero counts", () => {
    render(<StatusBar visibleCount={0} totalCount={0} />);
    expect(screen.getByText("Showing 0 of 0 problems")).toBeInTheDocument();
  });

  it("shows equal counts when no filter is active", () => {
    render(<StatusBar visibleCount={115} totalCount={115} />);
    expect(screen.getByText("Showing 115 of 115 problems")).toBeInTheDocument();
  });
});

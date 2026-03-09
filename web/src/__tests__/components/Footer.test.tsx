import { render, screen } from "@testing-library/react";
import { describe, it, expect } from "vitest";
import Footer from "../../components/Footer";

describe("Footer", () => {
  it("renders Buy me a coffee link", () => {
    render(<Footer />);
    const link = screen.getByText("☕ Buy me a coffee");
    expect(link).toBeInstanceOf(HTMLAnchorElement);
    expect(link).toHaveAttribute("href", "https://buymeacoffee.com/anipmehta");
    expect(link).toHaveAttribute("target", "_blank");
  });

  it("renders Star on GitHub link", () => {
    render(<Footer />);
    const link = screen.getByText("⭐ Star on GitHub");
    expect(link).toHaveAttribute("href", "https://github.com/anipmehta/AlgoForge");
    expect(link).toHaveAttribute("target", "_blank");
  });

  it("renders anipmehta profile link", () => {
    render(<Footer />);
    const link = screen.getByText("anipmehta");
    expect(link).toHaveAttribute("href", "https://github.com/anipmehta");
    expect(link).toHaveAttribute("target", "_blank");
  });
});

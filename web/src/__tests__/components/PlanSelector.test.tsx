import { describe, it, expect, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import PlanSelector from "../../components/PlanSelector";
import type { StudyPlan } from "../../lib/studyPlanTypes";

function makePlan(overrides: Partial<StudyPlan> = {}): StudyPlan {
  return {
    id: "plan-1",
    name: "Test Plan",
    description: "A test study plan",
    author: "tester",
    tags: ["interview", "arrays"],
    sections: [{ order: 1, focus: "Basics", problemNames: ["Two Sum"] }],
    ...overrides,
  };
}

const defaultProps = {
  plans: [] as StudyPlan[],
  activePlanId: null as string | null,
  onSelectPlan: vi.fn(),
  onDeactivatePlan: vi.fn(),
};

describe("PlanSelector", () => {
  it("renders plan cards with name, description, and author", () => {
    const plan = makePlan({
      name: "Blind 75",
      description: "Classic interview prep",
      author: "community",
    });
    render(<PlanSelector {...defaultProps} plans={[plan]} />);

    expect(screen.getByText("Blind 75")).toBeInTheDocument();
    expect(screen.getByText("Classic interview prep")).toBeInTheDocument();
    expect(screen.getByText("By community")).toBeInTheDocument();
  });

  it("renders tags for each plan", () => {
    const plan = makePlan({ tags: ["interview", "classic"] });
    render(<PlanSelector {...defaultProps} plans={[plan]} />);

    expect(screen.getByText("interview")).toBeInTheDocument();
    expect(screen.getByText("classic")).toBeInTheDocument();
    expect(screen.getByRole("list", { name: `Tags for ${plan.name}` })).toBeInTheDocument();
  });

  it("shows Select button for non-active plans", () => {
    const plan = makePlan();
    render(<PlanSelector {...defaultProps} plans={[plan]} activePlanId={null} />);

    expect(screen.getByRole("button", { name: `Select ${plan.name}` })).toBeInTheDocument();
    expect(screen.queryByRole("button", { name: `Deactivate ${plan.name}` })).not.toBeInTheDocument();
  });

  it("shows Deactivate button for the active plan", () => {
    const plan = makePlan({ id: "active-plan" });
    render(<PlanSelector {...defaultProps} plans={[plan]} activePlanId="active-plan" />);

    expect(screen.getByRole("button", { name: `Deactivate ${plan.name}` })).toBeInTheDocument();
    expect(screen.queryByRole("button", { name: `Select ${plan.name}` })).not.toBeInTheDocument();
  });

  it("calls onSelectPlan with plan id when Select is clicked", async () => {
    const onSelectPlan = vi.fn();
    const plan = makePlan({ id: "plan-abc" });
    render(<PlanSelector {...defaultProps} plans={[plan]} onSelectPlan={onSelectPlan} />);

    await userEvent.click(screen.getByRole("button", { name: `Select ${plan.name}` }));
    expect(onSelectPlan).toHaveBeenCalledWith("plan-abc");
  });

  it("calls onDeactivatePlan when Deactivate is clicked", async () => {
    const onDeactivatePlan = vi.fn();
    const plan = makePlan({ id: "plan-abc" });
    render(
      <PlanSelector
        {...defaultProps}
        plans={[plan]}
        activePlanId="plan-abc"
        onDeactivatePlan={onDeactivatePlan}
      />
    );

    await userEvent.click(screen.getByRole("button", { name: `Deactivate ${plan.name}` }));
    expect(onDeactivatePlan).toHaveBeenCalledOnce();
  });

  it("applies plan-card-active class to the active plan card", () => {
    const plan = makePlan({ id: "active-one" });
    const { container } = render(
      <PlanSelector {...defaultProps} plans={[plan]} activePlanId="active-one" />
    );

    const card = container.querySelector(".plan-card");
    expect(card).toHaveClass("plan-card-active");
  });
});

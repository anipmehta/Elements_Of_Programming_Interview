interface StatusBarProps {
  visibleCount: number;
  totalCount: number;
}

export default function StatusBar({ visibleCount, totalCount }: StatusBarProps) {
  return (
    <div className="status-bar">
      Showing {visibleCount} of {totalCount} problems
    </div>
  );
}

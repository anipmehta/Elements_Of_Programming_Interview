const STORAGE_KEY = "study-plans-active";

export interface PlanStore {
  getActivePlanId(): string | null;
  setActivePlan(id: string): void;
  clearActivePlan(): void;
  /** True if localStorage is unavailable and we're using in-memory fallback */
  readonly isMemoryFallback: boolean;
}

function isLocalStorageAvailable(): boolean {
  try {
    const key = "__storage_test__";
    localStorage.setItem(key, "1");
    localStorage.removeItem(key);
    return true;
  } catch {
    return false;
  }
}

export function createPlanStore(): PlanStore {
  const useMemory = !isLocalStorageAvailable();
  let activePlanId: string | null = useMemory
    ? null
    : localStorage.getItem(STORAGE_KEY);

  return {
    get isMemoryFallback() {
      return useMemory;
    },
    getActivePlanId() {
      return activePlanId;
    },
    setActivePlan(id: string) {
      activePlanId = id;
      if (!useMemory) localStorage.setItem(STORAGE_KEY, id);
    },
    clearActivePlan() {
      activePlanId = null;
      if (!useMemory) localStorage.removeItem(STORAGE_KEY);
    },
  };
}

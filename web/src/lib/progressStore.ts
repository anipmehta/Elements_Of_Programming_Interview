const STORAGE_KEY = "study-tracker-completed";

export interface ProgressStore {
  getCompleted(): Set<string>;
  markCompleted(name: string): void;
  markIncomplete(name: string): void;
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

function loadFromStorage(): Set<string> {
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (raw === null) return new Set();
    const parsed = JSON.parse(raw);
    if (!Array.isArray(parsed)) {
      // Corrupted — reset
      localStorage.removeItem(STORAGE_KEY);
      return new Set();
    }
    return new Set(parsed.filter((v: unknown) => typeof v === "string"));
  } catch {
    // Corrupted JSON — reset
    localStorage.removeItem(STORAGE_KEY);
    return new Set();
  }
}

function saveToStorage(completed: Set<string>): void {
  localStorage.setItem(STORAGE_KEY, JSON.stringify([...completed]));
}

export function createProgressStore(): ProgressStore {
  const useMemory = !isLocalStorageAvailable();
  let completed = useMemory ? new Set<string>() : loadFromStorage();

  return {
    get isMemoryFallback() {
      return useMemory;
    },
    getCompleted() {
      return new Set(completed);
    },
    markCompleted(name: string) {
      completed.add(name);
      if (!useMemory) saveToStorage(completed);
    },
    markIncomplete(name: string) {
      completed.delete(name);
      if (!useMemory) saveToStorage(completed);
    },
  };
}

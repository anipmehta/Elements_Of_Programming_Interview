#!/usr/bin/env bash
#
# migrate.sh — Reads classification.json and moves each problem folder
# into the new pattern-based directory structure using git mv.
#
# Usage: bash migrate.sh
#
set -euo pipefail

CLASSIFICATION="classification.json"
LOG_FILE="migration.log"

# Clear log
> "$LOG_FILE"

log() {
  echo "$1" | tee -a "$LOG_FILE"
}

log "=== Migration started at $(date) ==="

# Get all keys from classification.json
KEYS=$(jq -r 'keys[]' "$CLASSIFICATION")

for KEY in $KEYS; do
  PATTERN=$(jq -r --arg k "$KEY" '.[$k].pattern' "$CLASSIFICATION")
  SUB_PATTERN=$(jq -r --arg k "$KEY" '.[$k].subPattern // empty' "$CLASSIFICATION")

  # Determine the source path on the filesystem
  SOURCE="src/$KEY"

  # The problem folder name is the last path segment
  PROBLEM_NAME=$(basename "$KEY")

  # Build the target path
  if [ -n "$SUB_PATTERN" ]; then
    TARGET="src/${PATTERN}/${SUB_PATTERN}/${PROBLEM_NAME}"
  else
    TARGET="src/${PATTERN}/${PROBLEM_NAME}"
  fi

  # Check if source exists
  if [ ! -d "$SOURCE" ]; then
    log "WARNING: Source not found: $SOURCE (key: $KEY) — skipping"
    continue
  fi

  # Check if target already exists
  if [ -d "$TARGET" ]; then
    log "WARNING: Target already exists: $TARGET (key: $KEY) — skipping"
    continue
  fi

  # Perform the move
  git mv "$SOURCE" "$TARGET"
  log "MOVED: $SOURCE -> $TARGET"
done

log ""
log "=== Cleaning up empty company wrapper directories ==="

# List of known company wrapper directories to clean up
COMPANY_DIRS=(
  "src/facebook_practice"
  "src/doordash"
  "src/goldman_sachs"
  "src/microsoft_preparation"
  "src/palantir"
  "src/amazon_oa_2020_Jul"
  "src/twitter_coding_challenge"
  "src/warner_bros_discovery"
  "src/geli_take_home"
  "src/google_code_sample_domino_rotation"
  "src/google_code_sample_q1"
  "src/google_code_sample_q2"
  "src/google_code_sample_water_plants"
)

for DIR in "${COMPANY_DIRS[@]}"; do
  if [ -d "$DIR" ]; then
    # Check if directory is empty (or only contains empty subdirs)
    if [ -z "$(find "$DIR" -type f 2>/dev/null)" ]; then
      # Use git rm for tracked empty dirs, or just rmdir
      rm -rf "$DIR"
      log "REMOVED empty directory: $DIR"
    else
      log "WARNING: Directory not empty, skipping cleanup: $DIR"
    fi
  fi
done

log ""
log "=== Migration completed at $(date) ==="
log "Review $LOG_FILE for full audit trail."

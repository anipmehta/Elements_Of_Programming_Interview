#!/bin/bash
# update_packages.sh
# Updates Java package declarations to match directory paths relative to src/
# Also fixes cross-problem import statements that reference old package paths

set -e

SRC_DIR="src"
LOG_FILE="package_update.log"
> "$LOG_FILE"

echo "=== Updating Java package declarations ==="

# Function to derive the correct Java package name from a directory path relative to src/
derive_package() {
    local rel_dir="$1"
    # Replace / with .
    local pkg="${rel_dir//\//.}"
    # Prefix components that start with a digit with underscore
    # e.g., 01_arrays_and_strings -> _01_arrays_and_strings
    pkg=$(echo "$pkg" | sed -E 's/(^|\.)([0-9])/\1_\2/g')
    echo "$pkg"
}

updated=0
inserted=0
import_fixed=0

# Process all .java files under src/, excluding util/, tree/, package_layout/
find "$SRC_DIR" -name "*.java" \
    -not -path "$SRC_DIR/util/*" \
    -not -path "$SRC_DIR/tree/*" \
    -not -path "$SRC_DIR/package_layout/*" | sort | while read -r java_file; do

    # Get directory relative to src/
    dir_path=$(dirname "$java_file")
    rel_dir="${dir_path#$SRC_DIR/}"

    # Derive the correct package name
    correct_package=$(derive_package "$rel_dir")

    # Check if file has a package declaration
    if grep -q "^package " "$java_file"; then
        current_package=$(grep "^package " "$java_file" | head -1 | sed 's/^package //; s/;.*$//')
        if [ "$current_package" != "$correct_package" ]; then
            # Replace the existing package declaration
            sed -i '' "s/^package .*;/package ${correct_package};/" "$java_file"
            echo "UPDATED: $java_file: '$current_package' -> '$correct_package'" >> "$LOG_FILE"
            echo "UPDATED: $java_file"
        else
            echo "OK: $java_file (already correct)" >> "$LOG_FILE"
        fi
    else
        # Check if the file is entirely commented out (like SolutionTest.java)
        # Only insert package if the file has actual code
        if grep -q "^[^/]" "$java_file" 2>/dev/null; then
            # Insert package declaration at the top of the file
            # Use a temp file approach for portability
            tmp_file=$(mktemp)
            echo "package ${correct_package};" > "$tmp_file"
            echo "" >> "$tmp_file"
            cat "$java_file" >> "$tmp_file"
            mv "$tmp_file" "$java_file"
            echo "INSERTED: $java_file: '$correct_package'" >> "$LOG_FILE"
            echo "INSERTED: $java_file"
        else
            echo "SKIPPED (all commented): $java_file" >> "$LOG_FILE"
            echo "SKIPPED (all commented): $java_file"
        fi
    fi
done

echo ""
echo "=== Fixing cross-problem imports ==="

# Fix the cross-problem import: microsoft_preparation.swap_adjacent_linked_list_nodes.ListNode
# The ListNode.java file is now at src/02_linked_lists/swap_adjacent_linked_list_nodes/ListNode.java
# Its new package is _02_linked_lists.swap_adjacent_linked_list_nodes
OLD_IMPORT="import microsoft_preparation.swap_adjacent_linked_list_nodes.ListNode;"
NEW_IMPORT="import _02_linked_lists.swap_adjacent_linked_list_nodes.ListNode;"

find "$SRC_DIR" -name "*.java" -not -path "$SRC_DIR/util/*" | while read -r java_file; do
    if grep -q "$OLD_IMPORT" "$java_file"; then
        sed -i '' "s|$OLD_IMPORT|$NEW_IMPORT|g" "$java_file"
        echo "IMPORT FIXED: $java_file" >> "$LOG_FILE"
        echo "IMPORT FIXED: $java_file"
    fi
done

echo ""
echo "=== Package update complete ==="
echo "See $LOG_FILE for details"

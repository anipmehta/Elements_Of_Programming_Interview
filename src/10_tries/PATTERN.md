# Tries

## Description

A trie (prefix tree) stores strings character by character in a tree structure, enabling efficient prefix-based search, autocomplete, and dictionary operations. Each node represents a character, and paths from root to marked nodes form stored words.

## When to Apply

- The problem involves prefix matching, autocomplete, or dictionary lookup
- You need to search for words with shared prefixes efficiently
- The problem asks to find all words starting with a given prefix
- Wildcard or pattern matching over a dictionary is required

## Complexity Characteristics

| Operation | Time | Space |
|---|---|---|
| Insert a word | O(L) | O(L) per word |
| Search / prefix check | O(L) | O(1) |
| Autocomplete (all matches) | O(L + K) | O(1) |

Where L = word length, K = number of matches.

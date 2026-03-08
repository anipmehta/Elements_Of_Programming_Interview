## Problem Statement:
https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
#### Assumptions:
Input String only contains lowercase alphabetic char's
#### Example
###### Input
```
S = "aaabbbcc"
```
###### Output
```aidl
2 
Explanation: You can delete two 'b's resulting in the good string "aaabcc".
Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
```
## Analysis
`let K` = `max_char_frequency`

`let N` = `string length`

```
Time Complexity : O(N*k) Worst Case ~ if we have all chars repeating K times
Space Complexity : O(N) since we know string has lowercase english char hence N=26 so it will be O(26) which is 
constant space
```

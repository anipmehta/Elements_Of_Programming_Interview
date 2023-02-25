## Problem Statement:

Find the largest perimeter of an island given a 2 dimensional array of 1's and 0's representing land and water respectively.

Land = 1
Water = 0
Island = contiguous 1's that are adjacent to other 1's to the left, right, up, or down

Edge of an island = any 1 that is adjacent to a 0 OR grid boundary to the left, right, up, or down

Perimeter = total # of 1's on the edge of an island
#### Assumptions:
#### Example
Input:
[[1, 0, _1, _1, _1],
[1, 0, _1, 1, _1],
[0, 1, 0, _1, _1]]

Output: 7 (Island on the right)


Example 2:

Input:
[
[0, 0, 0, 0, 0, 0, 0],
[0, 1, 0, _1, _1, 1_, 0],
[0, 1, 0, _1, 1, 1_, 0],
[0, 0, _1, _1, _1, 1_, 0],
[0, 0, 0, 0, 0, 0, 0]
]

Output: 9 (Island on the right)


## Analysis

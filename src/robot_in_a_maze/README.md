## Problem Statement:
Initially a robot is placed at bottom left corner and exit is at bottom right corner.
From any point, a robot can move only in 2 directions right, top-right and bottom right and it can't go out of matrix.
Find total number of ways for the robot to kove from bottom-left to bottom-right of the matrix.

#### Assumptions:

A brute-force solution would be to try all possible path from bottom left corner and when we reach the bottom-right cell we increment the counter.
But the time complexity of this solution would be 3^(N*M) which is very slow.

Expected Complexity:
* Time - O(N*M)
* Space - O(N*M)
#### Example
###### Input
```aidl
M=3 , N=4 
```
###### Output
```aidl
4
```
## Analysis

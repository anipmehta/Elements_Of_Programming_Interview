##Problem Statement:
Let A be a 2D array whose entries are either W or B. Write a program that takes A, and replaces all W's that cannot reach the boundary with a B
####Intuition :
Compute the complement of the desired result. Start from boundaries and look for W's and replace the all white neighbours as reachable.

####Example
######Input
```
A = [[B B B B]
     [W B W B]
     [B W W B]
     [B B B B]]
```
######Output
```
A = [[B B B B]
     [W B B B]
     [B B B B]
     [B B B B]]
```

## Analysis
Since we are using DFS to solve this algorithm so the time and space complexity should be linear in size of Matrix
```
N - number of rows
M - number of cols
```
* Time - O(NM)
* Space - O(NM)
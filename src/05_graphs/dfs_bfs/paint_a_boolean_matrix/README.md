##Problem Statement:
Given a 2d matrix where each cell is either of two different colors and a location of the cell (row,col).
We have to paint the all the adjacent colors of the given cell by flipping the color of the current cell.
The two cells are said to be adjacent of they are to the left, right, bottom or up to each other. 

####Assumptions:
For the sake of this problem lets assume the two colors are reporesented by binary digits 0 and 1.

####Example
######Input
```
A = [[0 0 1]
     [1 0 0]
     [0 1 1]]
Cell Location - (0,1)
```
######Output
```
A = [[1 1 1]
     [1 1 1]
     [0 1 1]]

```
## Analysis
Since we are only visiting every cell once in worst case so the time complexity should in linear in terms of size of the matrix
```
N - number of rows
M - number of cols
```
* Time - O(NM)
* Space - O(NM)
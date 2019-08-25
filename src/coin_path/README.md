## Problem Statement:
Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer B denotes that from any place (suppose the index is i) in the array A, you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to. Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in the array.

Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.

If there are multiple paths with the same cost, return the lexicographically smallest such path.

If it's not possible to reach the place indexed N then you need to return an empty array.
#### Assumptions:
```aidl
B/F solution:
Enumerate all possible paths from any index and find the min cost path
A = [1,2,4,-1, 2]
B = 2
Recursive Tree : 
                                 idx = 1
                            /                 \
                      idx = 2                idx = 3 
                     /            \         /      \
                 idx = 3        idx = 4   idx = 4  idx = 5
                 /      \          |          |      
             idx = 4  idx = 5    idx=5       idx=5
As you can see the time complexity of this approach will be exponential (Time - O(N^B)).
```
* Why is this happening?
    * Answer: As you can see we are recalculating minimum cost from each index to the end multiple times.
    * See in the above tree idx = 3 subtrees are repeated several times and similarly subtree rooted at idx = 4 and idx = 5
    * Making the time complexity exponential
* Hint ?
    * Memoization min cost from each index to the end such that we only calculate min cost from each index to end once which makes the time complexity polynomial 
#### Example
###### Input
```aidl
A = [1,2,4,-1,2]
B = 2
```
###### Output
```aidl
O/P =  [1,3,5]
```
## Analysis
```aidl
N -> A.length
B -> Possible Jumps starting from any index
Time - O(N*B)
Space - O(N) for memoisation and recursive stack space (which can be saved if implemented in a iterative fashion)
```
## Problem Statement:
You are provided a set of positive integers (an array of integers). Each integer represents a number of nights users request on Airbnb.com. If you are a host, you need to design and implement an algorithm to find out the maximum number of nights you can accommodate. The constraint is that you have to reserve at least one day between each request, so that you have time to clean the room.
#### Assumptions:
```aidl
Linear Time Complexity expected not bruteforce exponential algorithm
```
#### Example
```aidl
A = [1,2,3];
O/P = 4 (Pick 1 and 3)
A = [51,2,6]
O/P = 11 (Pick 5 and 6)

```
## Analysis
```
N - Length of Users or Input
O(N)
```
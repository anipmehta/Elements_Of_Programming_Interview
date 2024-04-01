## Problem Statement:
ou are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
#### Assumptions:
[Given] Each list is sorted in ascending order
[Given] K sorted lists
#### Example

###### Input
lists = [[1,4,5],[1,3,4],[2,6]]
l1 = 1->4->5
l2 = 1->3->4
l3 = 2->6
###### Output
O/P = [1->1->2->3->4->5->6]
## Analysis
Min Heap of size K
Algo:
- Initialize heap with LL heads
  - while(!heap.isEmpty()){
    - min = pop the element
    - add it to the end of the new LL
    - if(min.next!=null){
      - add(min.next)}

T: O(N)*(logK) N: max size of LL
S: O(K) + [O(NK) for new LL]

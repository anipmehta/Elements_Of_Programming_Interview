## Problem Statement:
I have two registers: one for take-out orders, and the other for the other folks eating inside the cafe. All the customer orders get combined into one list for the kitchen, where they should be handled first-come, first-served.

Recently, some customers have been complaining that people who placed orders after them are getting their food first. Yikes—that's not good for business!

To investigate their claims, one afternoon I sat behind the registers with my laptop and recorded:

* The take-out orders as they were entered into the system and given to the kitchen. (takeOut)
* The dine-in orders as they were entered into the system and given to the kitchen. (dineIn)
* Each customer order as it was finished by the kitchen. (servedOrders)
* Given all three arrays, write a method to check that my service is first-come, first-served. All food should come out in the same order customers requested it.

#### Assumptions:
We'll represent each customer order as a unique integer.

#### Example

```aidl
Take_Out = [1,5,6]
Dine_In =  [2,4,7]
Orders_Processed = [1,2,4,6,5,7]
O/P = False
Orders_Processed = [1,2,4,5,6,7]
O/P = True

```
## Analysis
` N - Length of Orders Processed `
* Time - O(n)
* Space - O(1)

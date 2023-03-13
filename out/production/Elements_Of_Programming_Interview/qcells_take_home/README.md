## Overview
Take Home assignment for Geli.

For requirements refer the attached REQUIREMENT.md file

## Assumptions
1. For the sake of this problem acceleration is directly a function of hpw, formula used here is 
   1. newSpeed = currentSpeed + acceleration_factor * hpw
2. For racing exercise:
   1. both cars start from rest (aka 0 speed);
   2. both car start at time 0;
3. For Parking Lot exercise:
   1. Car's are parked in FIFO (First in First Out) manner using Queue as a data structure
   2. Car's are removed using the same logic of FIFO from the queue if the parking lot is full
   3. Parking Spot number is randomly assigned with an upper limit on capacity

JavaDoc is provided for each public method

## Output






## Future Optimization
1. ParkingLot park and remove can happen in constant time even if we don't use a FIFO method by using a Deque (Double Ended Queue)

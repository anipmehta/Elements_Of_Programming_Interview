## Overview
Take Home assignment for Geli.

For requirements refer the attached TakeHomeAssignment.docx file

## Assumptions
1. For the sake of this problem acceleration is `only and only`a function of `hpw (horsepower)`, formula used here is 
   1. `newSpeed = currentSpeed + acceleration_factor * hpw`
2. For racing exercise:
   1. `both cars start from rest (aka 0 speed);`
   2. `both car start at time 0;`
3. For Parking Lot exercise:
   1. Car's are parked in `FIFO (First in First Out) manner using Queue` as a data structure
   2. Car's are `removed using the same logic of FIFO` from the queue if the parking lot is full
   3. Parking Spot number is `randomly assigned with an upper limit on capacity`

`Note: JavaDoc is provided for each public method in the definition`

## Instructions to Run
### Terminal
```aidl 
1. Download the zip file
2. Unzip the file
3. Switch directory to the package file using eg: cd Downloads/{packageName}
4. Execute 'javac {packageName}/Assignment.java'
5. Make sure the above command executes without an error
6. Execute 'java {packageName}/Assignment.class'
6. You should the output exactly as described in the ## Output section below
```

## Output

```aidl
Executing exercise 3....

Toggling roof from:Closed to:Open
----Car's Characteristic after step: 1
Name: Prius;Current Speed: 25.200000000000003;Horse Power: 121.0;Mileage: 53.0
Name: Porsche Boxster;Current Speed: 54.0;Horse Power: 265.0;Mileage: 32.0
----------------------------------------------
----Car's Characteristic after step: 2
Name: Prius;Current Speed: 49.400000000000006;Horse Power: 121.0;Mileage: 53.0
Name: Porsche Boxster;Current Speed: 107.0;Horse Power: 265.0;Mileage: 32.0
----------------------------------------------
----Car's Characteristic after step: 3
Name: Prius;Current Speed: 73.60000000000001;Horse Power: 121.0;Mileage: 53.0
Name: Porsche Boxster;Current Speed: 160.0;Horse Power: 265.0;Mileage: 32.0
----------------------------------------------
----Car's Characteristic after step: 4
Name: Prius;Current Speed: 97.80000000000001;Horse Power: 121.0;Mileage: 53.0
Name: Porsche Boxster;Current Speed: 213.0;Horse Power: 265.0;Mileage: 32.0
----------------------------------------------
End of exercise 3 


Executing exercise 5....
-----Parking Lot State-----
Name: San Francisco Parking Lot
Capacity: 3
Spot: Id: 2, Car: Name: Prius;Production Number: ToyotaPriusP1;Car Type: Sedan;Horse Power: 121.0;Mileage: 53.0
Spot: Id: 0, Car: Name: Mini Cooper;Production Number: MiniM1;Car Type: Hatchback;Horse Power: 200.0;Mileage: 15.0
Spot: Id: 1, Car: Name: Porsche Boxster;Production Number: PorscheBoxsterB1;Car Type: Convertible;Horse Power: 265.0;Mileage: 32.0
----------------------

Error parking the car. The lot is at full capacity: 3
Name: Prius;Production Number: ToyotaPriusP1;Car Type: Sedan;Horse Power: 121.0;Mileage: 53.0
-----Parking Lot State-----
Name: San Francisco Parking Lot
Capacity: 3
Spot: Id: 2, Car: Name: BMW;Production Number: BMW330X1;Car Type: Sedan;Horse Power: 220.0;Mileage: 10.0
Spot: Id: 0, Car: Name: Mini Cooper;Production Number: MiniM1;Car Type: Hatchback;Horse Power: 200.0;Mileage: 15.0
Spot: Id: 1, Car: Name: Porsche Boxster;Production Number: PorscheBoxsterB1;Car Type: Convertible;Horse Power: 265.0;Mileage: 32.0
----------------------

End of exercise 5

```

## TroubleShooting
1. Make sure you are using jdk 1.7 or above
2. Make sure you are in the correct project directory
3. If issue still exists reach out to author anipmehta13@gmail.com

## Future Optimization
1. ParkingLot park and remove can happen in constant time even if we don't use a FIFO method by using a Deque (Double Ended Queue)

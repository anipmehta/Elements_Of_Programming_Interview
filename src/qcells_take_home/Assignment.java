package qcells_take_home;

public class Assignment {
    public static void main(String [] args) throws Exception{
        // Exercise 3

        /*

        README:
        Start out with creating a Prius with 5 seats and an engine with 121hp / 53MPG as well as a Porsche Boxster
        convertible with two seats, 265hp and 32MPG.
        Lower the roof of the Boxster and start racing the Prius with 20% acceleration each for the first one
        to hit a speed of 200 (call acceleration method multiple times). Print the speeds of both cars after each
        acceleration step

         */
        System.out.println("\nExecuting exercise 3....\n");
        Car prius = new Car("Prius", 5, 0, "ToyotaPriusP1", "Sedan", new Engine(121, 53));
        ConvertibleCar boxster = new ConvertibleCar("Porsche Boxster", 2, 0, "PorscheBoxsterB1", "Convertible", new Engine(265,32));
        // Lowering boxster roofTop
        boxster.toggleRoof();
        // Race both cars until 200 miles per hour
        int time = 1;
        while(boxster.getSpeed() < 200 && prius.getSpeed() < 200){
            prius.accelerate(0.2);
            boxster.accelerate(0.2);
            System.out.println("----Car's Characteristic after step: " + time++);
            System.out.println(prius.getCharacteristic());
            System.out.println(boxster.getCharacteristic());
            System.out.println("----------------------------------------------");
        }
        System.out.println("End of exercise 3 \n");
        // End of Exercise of 3

        // Exercise 5
        /*
        README:
        Create a lot of size three and two more cars. Try to park all the cars in the lot. Remove one when the lot
        is already at full capacity when trying to park a new car. Finally, print the directory of the lot.

         */

        System.out.println("\nExecuting exercise 5....");
        ParkingLot parkingLot = new ParkingLot("San Francisco Parking Lot", 3);
        Car mini = new Car("Mini Cooper", 4, 0, "MiniM1", "Hatchback", new Engine(200, 15));
        Car beamer = new Car("BMW", 4, 0, "BMW330X1", "Sedan", new Engine(220, 10));
        parkingLot.parkCar(prius);
        parkingLot.parkCar(boxster);
        parkingLot.parkCar(mini);
        // state of parking lot
        System.out.println(parkingLot);
        // Exception will be raised
        try {
            parkingLot.parkCar(beamer);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        // removing car
        Car removedCar = parkingLot.removeCar();
        // this car should be prius according to FIFO logic that
        System.out.println(removedCar);
        parkingLot.parkCar(beamer);
        System.out.println(parkingLot);

        System.out.println("End of exercise 5\n");
        // End of exercise 5
    }
}

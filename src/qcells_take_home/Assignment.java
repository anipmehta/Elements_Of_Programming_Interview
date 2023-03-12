package qcells_take_home;

public class Assignment {
    public static void main(String [] args) throws Exception{
        // Exercise 3
        Car prius = new Car("Prius", 5, 0, "ToyotaPriusP1", "Sedan", new Engine(121, 53));
        Car boxster = new Car("Porsche Boxster", 2, 0, "PorscheBoxsterB1", "Convertible", new Engine(265,32));
        // TODO: lower the roof of boxster
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

        // Exercise 5

        ParkingLot parkingLot = new ParkingLot("Take Home Parking Lot", 3);
        Car mini = new Car("Mini Cooper", 4, 0, "MiniM1", "Hatchback", new Engine(200, 15));
        Car beamer = new Car("BMW", 4, 0, "BMW330X1", "Sedan", new Engine(220, 10));
        parkingLot.parkCar(prius);
        parkingLot.parkCar(boxster);
        parkingLot.parkCar(mini);
        // state of parking lot
        System.out.println(parkingLot);
        // Exception will be raised
//         parkingLot.parkCar(beamer);
        Car removedCar = parkingLot.removeCar();
        // this car should be prius according to FIFO
        System.out.println(removedCar);
        parkingLot.parkCar(beamer);
        System.out.println(parkingLot);
    }
}

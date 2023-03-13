package geli_take_home;

import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashSet;

/**
 * Parking Lot which maintains a directory of spots and cars
 *
 * Exercise 4
 *
 * Create a parking lot with methods to park and remove a car. The spots should be enumerated
 * and the lot should maintain a directory which car is parked on which spot.
 * If someone tries to park a car when the lot is at full capacity an error should be raised.
 *
 */
public class ParkingLot {
    private Set<Integer> sportIds;
    String name;
    private int capacity;
    Queue<Spot> spotQueue;
    Map<Spot, Car> spotCarMap;

    public ParkingLot(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        spotQueue = new LinkedList<>();
        spotCarMap = new HashMap<>();
        sportIds = new HashSet<>();
    }

    /**
     * Parks a car at a random available spot, if parking lot if at full capacity throws an exception
     *
     * @param car car
     * @return Spot object
     * @throws Exception
     */
    public Spot parkCar(Car car) throws Exception{
        if(isAtCapacity()){
            throw new Exception("Error parking the car. The lot is at full capacity: " + this.capacity);
        }
        Spot spot = createNewSpot();
        spotQueue.add(spot);
        spotCarMap.put(spot, car);
        return spot;
    }

    private Spot createNewSpot(){
        Random random = new Random();
        int spotNumber = random.nextInt(this.capacity);
        while(sportIds.contains(spotNumber)){
            spotNumber = random.nextInt(this.capacity);
        }
        sportIds.add(spotNumber);
        return new Spot(String.valueOf(spotNumber));
    }

    private boolean isAtCapacity(){
        return this.capacity == spotQueue.size();
    }

    /**
     * Removes the first car that was added/parked in the parking lot
     *
     * If parking lot is empty throws an IllegalArgumentException
     *
     * @return Car object
     * @throws IllegalArgumentException
     */
    public Car removeCar() throws IllegalArgumentException{
        if(spotQueue.isEmpty()){
            throw new IllegalArgumentException("Parking Lot is empty: " + this.toString());
        }
        // removing the first car that came in FIFO
        Spot remove = spotQueue.poll();
        Car car = spotCarMap.get(remove);
        sportIds.remove(Integer.parseInt(remove.getId()));
        spotCarMap.remove(remove);
        return car;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-----Parking Lot State-----" + "\n");
        stringBuilder.append("Name: " + this.name + "\n");
        stringBuilder.append("Capacity: " + this.capacity + "\n");
        for(Spot spot : this.spotCarMap.keySet()){
            stringBuilder.append("Spot: " + spot.toString() + ", Car: " + spotCarMap.get(spot).toString() + "\n");
        }
        stringBuilder.append("----------------------" + "\n");
        return stringBuilder.toString();
    }

}

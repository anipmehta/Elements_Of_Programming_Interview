package qcells_take_home;

import java.util.*;

public class ParkingLot {
    String name;
    private int capacity;
    Queue<Spot> spotQueue;
    Map<Spot, Car> spotCarMap;

    public ParkingLot(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        spotQueue = new LinkedList<>();
        spotCarMap = new HashMap<>();
    }

    public Spot parkCar(Car car) throws Exception{
        if(isAtCapacity()){
            throw new Exception("Parking lot is at full capacity: " + this.capacity);
        }
        Spot spot = createNewSpot();
        spotQueue.add(spot);
        spotCarMap.put(spot, car);
        return spot;
    }

    private Spot createNewSpot(){
        Random random = new Random();
        int spotNumber = random.nextInt(this.capacity);
        return new Spot(String.valueOf(spotNumber));
    }

    public boolean isAtCapacity(){
        return this.capacity == spotQueue.size();
    }

    public Car removeCar() throws IllegalArgumentException{
        if(spotQueue.isEmpty()){
            throw new IllegalArgumentException("Parking Lot is empty: " + this.toString());
        }
        // removing the first car that came in FIFO
        Spot remove = spotQueue.poll();
        Car car = spotCarMap.get(remove);
        spotCarMap.remove(remove);
        return car;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: " + this.name + "\n");
        stringBuilder.append("Capacity: " + this.capacity + "\n");
        for(Spot spot : this.spotCarMap.keySet()){
            stringBuilder.append("Spot: " + spot.toString() + ", Car: " + spotCarMap.get(spot).toString() + "\n");
        }
        return stringBuilder.toString();
    }

}

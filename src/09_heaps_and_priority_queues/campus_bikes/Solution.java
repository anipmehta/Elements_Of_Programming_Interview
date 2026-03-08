package campus_bikes;

import util.HeapItem;
import util.TwoDCoordinate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution{
    public static void main(String [] args){
        TwoDCoordinate w1 = new TwoDCoordinate(0,0);
        TwoDCoordinate w2 = new TwoDCoordinate(1,1);
        TwoDCoordinate w3 = new TwoDCoordinate(2,0);
        TwoDCoordinate b1 = new TwoDCoordinate(1,0);
        TwoDCoordinate b2 = new TwoDCoordinate(2,2);
        TwoDCoordinate b3 = new TwoDCoordinate(2,1);
        TwoDCoordinate [] workers = {w1, w2, w3};
        TwoDCoordinate [] bikes = {b1, b2, b3};
        int wIndex = 0;
        for(int bikeIndex : closestBikes(workers, bikes)){
            System.out.println("Worker -> " + wIndex++ + " is assigned bike index -> " + bikeIndex);
        }

    }
    public static int [] closestBikes(TwoDCoordinate[] workers, TwoDCoordinate[] bikes){
        Set<Integer> bikesUsed = new HashSet<>();
        Set<Integer> workersUsed = new HashSet<>();
        int [] bikesAssigned = new int[workers.length];
        PriorityQueue<HeapItem> heap = new PriorityQueue<>(workers.length * bikes.length, new Comparator<HeapItem>() {
            @Override
            public int compare(HeapItem o1, HeapItem o2) {
                if(o1.getValue("distance") == o2.getValue("distance")){
                    if(o1.getValue("workerIndex") == o2.getValue("workerIndex")){
                        return (int) o1.getValue("bikeIndex") - (int) o2.getValue("bikeIndex");
                    }
                    else{
                        return (int) o1.getValue("workerIndex") - (int) o2.getValue("workerIndex");
                    }
                }
                else{
                    return (int) o1.getValue("distance") - (int) o2.getValue("distance");
                }
            }
        });
        for(int i=0;i<workers.length;i++){
            for(int j=0;j<bikes.length;j++){
                int distance = workers[i].getManhattanDistance(bikes[i]);
                HeapItem heapItem = new HeapItem();
                heapItem.addKey("distance", distance);
                heapItem.addKey("workerIndex", i);
                heapItem.addKey("bikeIndex", j);
                heap.offer(heapItem);
            }
        }
        while (!heap.isEmpty()){
            HeapItem item = heap.poll();
            int bike = (int) item.getValue("bikeIndex");
            int worker = (int) item.getValue("workerIndex");
            if(workersUsed.contains(worker) || bikesUsed.contains(bike)){
                continue;
            }
            bikesAssigned[worker] = bike;
            bikesUsed.add(bike);
            workersUsed.add(worker);
        }
        return bikesAssigned;
    }
}

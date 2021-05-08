package microsoft_preparation.median_in_data_stream;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinderOptimized {
    Queue<Integer> minHeap;
    Queue<Integer> maxHeap;
    private int count;

    /*
    [2,3,4, 5]
    min = 4, 5
    max = 2,3
    */
    public MedianFinderOptimized() {
        count = 0;
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(1, Comparator.reverseOrder());
    }

    public void addNum(int num) {
        if (maxHeap.size() == 0 || num < maxHeap.peek()){
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if (maxHeap.size() > minHeap.size() + 1){
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()){
            maxHeap.add(minHeap.poll());
        }
        count++;
    }

    public double findMedian() {
        if(count%2 != 0){
            return maxHeap.peek();
        }
        return (double) (minHeap.peek() + maxHeap.peek()) / 2;
    }
}

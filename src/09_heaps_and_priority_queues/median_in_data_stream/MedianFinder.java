package _09_heaps_and_priority_queues.median_in_data_stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianFinder {
    List<Integer> list;
    public MedianFinder() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        list.add(num);
    }

    public double findMedian() {
        Collections.sort(list);
        int size = list.size();
        int mid = size/2;
        if(size%2 == 0){
           return (list.get(mid-1) + list.get(mid))/2;
        }
        return list.get(mid);
    }
}

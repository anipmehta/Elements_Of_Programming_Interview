package meeting_rooms_II;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        int [][] intervals = {
                {0,30},
                {5,10},
                {15,20}
        };
        int [][] intervals2 = {
                {7,10},
                {2,4}
        };
        System.out.println(minMeetingRooms(intervals));
        System.out.println(minMeetingRooms(intervals2));
    }
    public static class Intervals{
        public int start;
        public int end;
        public Intervals(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int minMeetingRooms(int[][] intervals) {
        if(intervals.length == 0){
            return 0;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        List<Intervals> intervalList = new ArrayList<>();
        for(int i=0;i<intervals.length;i++){
           intervalList.add(new Intervals(intervals[i][0], intervals[i][1]));
        }
        Collections.sort(intervalList, new Comparator<Intervals>() {
            @Override
            public int compare(Intervals o1, Intervals o2) {
                return o1.start -  o2.start;
            }
        });
       minHeap.offer(intervalList.get(0).end);
       for(int i=1;i<intervalList.size();i++){
           Intervals interval = intervalList.get(i);
           if(interval.start >= minHeap.peek()) {
               minHeap.poll();
           }
           minHeap.offer(interval.end);
       }
       return minHeap.size();
    }
}

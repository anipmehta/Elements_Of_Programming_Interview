package microsoft_preparation.meeting_rooms_ii;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    public int minMeetingRooms(int[][] intervals) {
        Queue<Integer> meetingQueue = new PriorityQueue<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int rooms = 0;
        for(int [] interval : intervals) {
            if(meetingQueue.isEmpty()){
                rooms++;
            }
            else{
                int endingMeeting = meetingQueue.peek();
                if(interval[0]< endingMeeting){
                    rooms++;
                }
                else{
                    meetingQueue.poll();
                }
            }
            meetingQueue.offer(interval[1]);
        }
        return rooms;
    }
}

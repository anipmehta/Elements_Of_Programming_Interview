package microsoft_preparation.sliding_window_maximum;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    /*
    start with intial window
        add elements to queue
            res.add(findMax())
            slide()
            remove first
            add i+1 to end
     */
    class Pair{
        int index;
        int value;
        public Pair(int index, int value){
            this.index = index;
            this.value = value;
        }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
    List<Integer> maximas = new ArrayList<>();
    Queue<Pair> pq = new PriorityQueue<>(k, new Comparator<Pair>() {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o2.value - o1.value;
        }
    });

    for(int i=0;i<k;i++){
        pq.offer(new Pair(i, nums[i]));
    }
    for(int i=k-1;i<nums.length;i++){
        pq.offer(new Pair(i, nums[i]));
        while (pq.peek().index <= i-k){
            pq.poll();
        }
        maximas.add(pq.peek().value);
    }

    int [] result = new int[maximas.size()];
    int index = 0;
    for(int n : maximas){
        result[index++] = n;
    }
    return result;
    }
}

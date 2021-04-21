package facebook_practice.k_closest_elements;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    class Pair{
        public int n;
        public int distanceFromX;
        public Pair(int n, int distance){
            this.n = n;
            this.distanceFromX = distance;
        }
    }
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(k, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o2.distanceFromX == o1.distanceFromX){
                    return o2.n - o1.n;
                }
                return o2.distanceFromX - o1.distanceFromX;
            }
        });
        for(int num : arr){
            maxHeap.offer(new Pair(num, Math.abs(num - x)));
            if(maxHeap.size() > k){
                maxHeap.poll();
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!maxHeap.isEmpty()){
            res.add(maxHeap.poll().n);
        }
        Collections.sort(res);
        return res;
    }
}

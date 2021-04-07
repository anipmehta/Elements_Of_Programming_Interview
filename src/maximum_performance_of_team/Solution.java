package maximum_performance_of_team;

import com.sun.scenario.effect.impl.sw.java.JSWEffectPeer;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    class Engineer{
        public int efficiency;
        public int speed;
        public Engineer(int efficiency, int speed){
            this.efficiency = efficiency;
            this.speed = speed;
        }
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        // take greedy approach here
        // start picking from the most efficient employees and pop out the one with smallest speed as we iterate

        Queue<Engineer> pq = new PriorityQueue<>(k, new Comparator<Engineer>() {
            @Override
            public int compare(Engineer o1, Engineer o2) {
                return o1.speed - o2.speed;
            }
        });
        long maxPerformance = 0;
        long totalSpeed = 0;
        List<Engineer> engineers = new ArrayList<>();
        for(int i=0;i<n;i++){
            engineers.add(new Engineer(efficiency[i], speed[i]));
        }
        Collections.sort(engineers, new Comparator<Engineer>() {
            @Override
            public int compare(Engineer o1, Engineer o2) {
                return o2.efficiency - o1.efficiency;
            }
        });
        for(Engineer engineer : engineers){
            pq.offer(engineer);
            totalSpeed += engineer.speed;
            if(pq.size()>k){
                Engineer slow = pq.poll();
                totalSpeed -= slow.speed;
            }

            maxPerformance = Math.max(maxPerformance, totalSpeed * engineer.efficiency);
            System.out.println(maxPerformance);
        }

        return (int) (maxPerformance % (long)(1e9 + 7));
    }
}

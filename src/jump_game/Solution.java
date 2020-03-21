package jump_game;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    public static void main(String [] args){
        int [] arr = {4,2,3,0,3,1,2};
        System.out.println(canReach(arr, 5));
        int [] arr2 = {3,0,2,1,2};
        System.out.println(canReach(arr2, 2));
    }
    public static boolean canReach(int [] arr, int start) {
        return helper(arr, start);
    }

    private static boolean helper(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()){
            int current = queue.poll();
            if(arr[current]==0){
                return true;
            }
            if (visited.contains(current)){
                continue;
            }
            visited.add(current);
            int left = current - arr[current];
            int right = current + arr[current];
            if(left >= 0) {
                queue.offer(left);
            }
            if(right < arr.length){
                queue.offer(right);
            }
        }
        return false;
    }
}

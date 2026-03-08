package coin_path;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String [] args){
        int [] A = {1,2,4,-1,2};
        int B = 1;
        System.out.println(cheapestJump(A, B));
    }
    public static int cheapestJumpCost(int [] A, int B, int [] next){
        int [] cache = new int[A.length];
        int cheapestPathCost = helper(A, B, 0, cache, next);
        return cheapestPathCost;
    }
    public static int helper(int [] A, int B, int index, int [] cache, int [] next){
        if(A[index] == -1){
            //returning infinity as we can't cross from the index which is -1
            return Integer.MAX_VALUE;
        }
        if(index==A.length-1){
            return A[index];
        }
        int minCost = Integer.MAX_VALUE;
        int nextIndex = index;
        for(int i=index+1;i<=index+B;i++){
            if(i>A.length-1){
                break;
            }
            if(cache[i]==0){
                cache[i] = helper(A, B, i, cache, next);
            }
            if(cache[i]<minCost){
                minCost = cache[i];
                nextIndex = i;
            }
            next[index] = nextIndex;
        }
        //check if it is possible to reach the end
        return minCost == Integer.MAX_VALUE ? Integer.MAX_VALUE : A[index] + minCost;
    }
    public static List<Integer> cheapestJump(int[] A, int B) {
        int [] next = new int[A.length];
        int cheapestCostPath = cheapestJumpCost(A, B, next);
        if(cheapestCostPath==Integer.MAX_VALUE){
            return new ArrayList<>();
        }
        List<Integer> cheapPath = new ArrayList<>();
        int start = 0;
        while(start!=A.length-1){
            cheapPath.add(start+1);
            start = next[start];
        }
        cheapPath.add(start+1);
        return cheapPath;
    }
}

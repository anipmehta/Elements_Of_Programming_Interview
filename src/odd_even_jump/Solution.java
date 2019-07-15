package odd_even_jump;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String [] args){
        int [] arr = {2,3,1,1,3};
        System.out.println(oddEvenJumps(arr));

    }
    public static int oddEvenJumps(int [] A){
      Map<Integer, Integer> valToIndex = new TreeMap<>();
      // dp to store boolean value if it is possible to reach the end from a index given odd(0th index) and
        // even(1st index) jumps
      boolean [][] dp = new boolean [A.length][2];
      valToIndex.put(A[A.length-1], A.length-1);
      dp[A.length-1][0] = true;
      dp[A.length-1][1] = true;
      for(int i=A.length - 2; i>=0; i--){
          if(valToIndex.containsKey(A[i])){
//               This means that the largest smaller and smallest larger value is the same number to the right of
//               that index
              dp[i][0] = dp[valToIndex.get(A[i])][1];
              dp[i][1] = dp[valToIndex.get(A[i])][0];
          }
          else{
              Integer higher = ((TreeMap<Integer, Integer>) valToIndex).higherKey(A[i]);
              Integer lower = ((TreeMap<Integer, Integer>) valToIndex).lowerKey(A[i]);
              if(higher!=null){
                  dp[i][0] = dp[valToIndex.get(higher)][1];
              }
              if(lower!=null){
                  dp[i][1] = dp[valToIndex.get(lower)][0];
              }
          }
          valToIndex.put(A[i], i);
      }
      int count = 0;
      for(int i=0;i<dp.length;i++){
          if(dp[i][0]){
              count++;
          }
      }
      return count;
    }
}

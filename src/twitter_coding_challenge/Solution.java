package twitter_coding_challenge;

import java.util.*;

public class Solution {
    public static int restock(List<Integer> itemCount, int target) {
        // Write your code here
        long currentSum = 0;
        for(int unit : itemCount){
            currentSum += unit;
            if(currentSum>target){
                return (int) (currentSum - target);
            }
        }
        return (int) (target - currentSum);
    }
    public static int getUniqueUserIdSum(List<Integer> arr) {
        // Write your code here
        int sum = 0;
        Set<Integer> set = new HashSet<>();
        Collections.sort(arr);
        for(int num : arr){
           if(set.contains(num)){
               set.add(num + 1);
               sum += num + 1;
           }
           else{
               set.add(num);
               sum += num;
           }
        }
        return sum;
    }
    public static String solve(int k, List<Integer> numbers) {
        // Write your code here
        if(helper(k, numbers)){
            return "Yes";
        }
        return "No";

    }
    public static boolean helper(int k, List<Integer> numbers){
        throw new UnsupportedOperationException();
    }
    public static int maximumTotalWeight(List<Integer> weights, List<Integer> tasks, int p) {
        // Write your code here
//        Arrays.fill/();
        return helper(weights, tasks, p, 0);
    }

    private static int helper(List<Integer> weights, List<Integer> tasks, int p, int i) {
        if(p<=0 || i > weights.size() - 1){
            return 0;
        }
        return Math.max(helper(weights, tasks, p, i+1),
                helper(weights, tasks, p-(tasks.get(i)*2), i+1) + weights.get(i));
    }
}

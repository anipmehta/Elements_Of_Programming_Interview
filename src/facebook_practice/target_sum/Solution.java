package facebook_practice.target_sum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public int findTargetSumWays(int[] nums, int target) {
        Map<String, Integer> cache = new HashMap<>();
        return helper(nums, 0, 0, target, cache);
    }

    private int helper(int[] nums, int index, int sum, int target, Map<String, Integer> cache) {
        if(index==nums.length && sum == target){
            return 1;
        }

        if(index>nums.length - 1){
            return 0;
        }
        // custom key
        String key = index + ":" + sum;
        if(!cache.containsKey(key)){
           cache.put(key, helper(nums, index+1, sum - nums[index], target, cache) +
                   helper(nums, index + 1,sum +  nums[index], target, cache));
        }

        return cache.get(key);

    }
}

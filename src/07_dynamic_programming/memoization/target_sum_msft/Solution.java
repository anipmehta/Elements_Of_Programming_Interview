package microsoft_preparation.target_sum;

public class Solution {
    public static void main(String [] args){
        Solution solution = new Solution();
        System.out.println(solution.findTargetSumWays(new int[] {1,1,1,1,1}, 3));
    }

    public int findTargetSumWays(int[] nums, int S) {
//        int [] cache = new int[S][nun]
        return helper(nums, S, 0);
    }

    private int helper(int[] nums, int target, int index) {
        if(index == nums.length){
            return target == 0 ? 1 : 0;
        }
        return helper(nums, target - nums[index], index+1)
                + helper(nums, target + nums[index], index+1);
    }
}

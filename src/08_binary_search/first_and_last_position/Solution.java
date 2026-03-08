package facebook_practice.first_and_last_position;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        Solution solution = new Solution();
        int [] arr = new int[]{5,7,7,8,8,10};
        int [] res = solution.searchRange(arr, 8);
        System.out.println(res[0] + "," + res[1]);
    }

    public int[] searchRange(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int [] res = new int[2];
        while (start<=end){
            int mid = start + (end - start) / 2;
            if(nums[mid]== target){
                return findIndex(nums, mid, target);
            }
            if(nums[mid]<target){
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        return new int[]{-1,-1};
    }
    public int[] findIndex(int [] nums, int curr, int target){
        int [] res = new int[2];
        int index = curr;
        if(nums[0]==target){
            res[0] = 0;
        }
        else {
            while(index >= 0 && nums[index]==target){
                index--;
            }
            res[0] = index+1;
        }
        index = curr;

        if(nums[nums.length-1]==target){
            res[1]=  nums.length - 1;
        }
        else{
            while (index<nums.length-1 && nums[index] == target){
                index++;
            }
            res[1] = index-1;
        }
        return res;
    }
}

package microsoft_preparation.sort_colors;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        Solution solution = new Solution();
        int [] arr = new int[]{2,0,2,1,1,0};
        solution.sortColors(arr);
        for(int i : arr){
            System.out.print(i + ",");
        }

    }

    public void sortColors(int[] nums) {
        int lastZero = 0;
        int firstTwo = nums.length-1;
        int i = 0;
        while(i<firstTwo) {
            if(nums[i]==0){
                swap(nums, lastZero, i);
                lastZero++;
                i++;
            }
            if(nums[i]==1){
                i++;
            }
            if(nums[i]==2){
                swap(nums, firstTwo, i);
                firstTwo--;
                i++;
            }

        }
    }

    private void swap(int[] nums, int lastZero, int i) {
        if(i==lastZero){
            return;
        }
        int temp = nums[i];
        nums[i] = nums[lastZero];
        nums[lastZero] = temp;
    }

}

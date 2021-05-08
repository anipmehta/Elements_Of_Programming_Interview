package microsoft_preparation.max_sliding_window;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        int [] arr = new int[]{};
        Solution solution = new Solution();
        System.out.println(solution.maxSlidingWindow(arr, 3));

    }
    // [1,-1, -3]

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int start = 0;
        int end = k-1;
        List<Integer> maxList = new ArrayList<>();
        while (end < nums.length){
            maxList.add(findMax(nums, start, end));
            start++;
            end++;
        }
        int [] res = new int[maxList.size()];
        int index = 0;
        for(int n : maxList){
            res[index++] = n;
        }
        return res;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if(a[0]>b[0]) {
                return -1;
            } else if(a[0]<b[0]) {
                return 1;
            }
            return 0;
        });

        for(int i=0; i<k; i++) {
            int[] val = {nums[i], i};
            queue.add(val);
        }

        int[] answer = new int[nums.length-k+1];
        answer[0] = queue.peek()[0];
        for(int i=k; i<nums.length; i++) {
            int[] val = {nums[i], i};
            queue.add(val);
            while(queue.peek()[1]<i-k+1) {
                queue.remove();
            }
            answer[i-k+1] = queue.peek()[0];
        }
        return answer;


    }

    public int findMax(int [] nums, int start, int end){
        int max = Integer.MIN_VALUE;
        for(int i=start;i<=end;i++){
            max = Math.max(max, nums[i]);
        }
        return max;
    }
}

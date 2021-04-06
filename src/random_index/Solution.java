package random_index;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    Map<Integer, List<Integer>> map;

    public Solution(int[] nums) {
        map = new HashMap<>();
        for(int i=0; i< nums.length ;i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> indexes = map.get(target);
        Random random = new Random();
        int randomIndex = random.nextInt(indexes.size());
        return indexes.get(randomIndex);
    }
}

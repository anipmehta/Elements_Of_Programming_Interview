package facebook_practice.three_sum;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    public List<List<Integer>> threeSum(int[] nums) {
      List<List<Integer>> res = new ArrayList<>();
      Set<String> dupes = new HashSet<>();
      for(int i=0;i<nums.length;i++){
          List<List<Integer>> pairs = twoSum(nums, i+1,nums.length-1, 0 - nums[i]);
          if(!pairs.isEmpty()){
              for(List<Integer> pair : pairs){
                  List triplet = new ArrayList();
                  triplet.add(nums[i]);
                  triplet.addAll(pair);
                  Collections.sort(triplet);
                  if(!dupes.contains(triplet.toString())){
                      res.add(triplet);
                      dupes.add(triplet.toString());
                  }
              }
          }
      }
    return res;
    }

    public List<List<Integer>> twoSum(int [] arr, int start, int end, int sum){
        Set<Integer> set = new HashSet<>();
        List<List<Integer>> pairs = new ArrayList<>();
        for(int i=start;i<end;i++){
            if(set.contains(sum-arr[i])){
                List<Integer> pair = new ArrayList<>();
                pair.add(sum-arr[i]);
                pair.add(arr[i]);
                pairs.add(pair);
            }
            set.add(arr[i]);
        }
        return pairs;
    }
}

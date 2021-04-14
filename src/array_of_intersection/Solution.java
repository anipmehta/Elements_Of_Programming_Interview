package array_of_intersection;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> intersection = new ArrayList<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for(int num : nums1){
            map1.put(num, map1.getOrDefault(num, 0) + 1);
        }
        for(int num : nums2){
            map2.put(num, map2.getOrDefault(num, 0) + 1);
        }
        for(int num : map1.keySet()){
            if(map2.containsKey(num)){
                int min = Math.min(map1.get(num),map2.get(num));
                for(int i=0;i<min;i++){
                    intersection.add(num);
                }
            }
        }
        int [] res = new int[intersection.size()];
        int index = 0;
        for(int num : intersection){
            res[index++] = num;
        }
        return res;
    }
}

package longest_substring_with_k_dsitinct;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String [] args){
        String S = "eceba";
        Solution solution = new Solution();
        int K = 2;
        System.out.println(solution.lengthOfLongestSubstringKDistinct(S, K));
    }
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> window = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int start = 0, end = 0;
        while(end < s.length()){
            window.put(s.charAt(end), window.getOrDefault(s.charAt(end), 0) + 1);
            while (window.size()> k && start < end){
                window.put(s.charAt(start), window.get(s.charAt(start)) - 1);
                if(window.get(s.charAt(start)) <= 0){
                    window.remove(s.charAt(start));
                }
                start++;
            }
            max = Math.max(max, end - start + 1);
            end++;
        }

       return max == Integer.MIN_VALUE ? 0 : max;
    }
}

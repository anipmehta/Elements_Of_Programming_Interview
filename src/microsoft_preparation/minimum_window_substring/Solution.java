package microsoft_preparation.minimum_window_substring;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        Solution solution = new Solution();
        System.out.println("minWindow -> " + solution.minWindow("ADOBECODEBANC", "ABC"));

    }

    /*
    S = "ADOBECODEBANC", T = "ABC"
    O/P = "BANC"
     */

    public String minWindow(String s, String t) {
        String minWindow = "";
        int start = 0;
        int end = 0;
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        for (char ch : t.toCharArray()) {
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }
        int min = Integer.MAX_VALUE;
        while (end<s.length()) {
            while (end<s.length() && !isSame(sMap, tMap)) {
                char ch = s.charAt(end++);
                sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
            }
            if(end == s.length() && !isSame(sMap, tMap)) {
                break;
            }
            if(min>end-start){
                min = end - start;
                minWindow = s.substring(start, end);
            }
            while(start < s.length() && isSame(sMap, tMap)) {
                char beg = s.charAt(start++);
                sMap.put(beg, sMap.get(beg) - 1);
                if (sMap.get(beg) == 0) {
                    sMap.remove(beg);
                }
            }
            start = start - 1;

        }

        return minWindow;
    }

    private boolean isSame(Map<Character, Integer> sMap, Map<Character, Integer> tMap) {
        for(char ch : tMap.keySet()) {
            if(!sMap.containsKey(ch)) {
                return false;
            }
            if(tMap.get(ch) != sMap.get(ch)) {
                return false;
            }
        }
        return true;
    }
}

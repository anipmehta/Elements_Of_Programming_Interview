package min_window_substring;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String [] args){
        Solution solution = new Solution();
        String S = "ADOBECODEBANC";
//        String S = "A";
//        String T = "A";
        String T =  "ABC";

        System.out.println(solution.minWindow(S, T));
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        String minSubString = "";
        int minLength = Integer.MAX_VALUE;

        for (char ch : t.toCharArray()){
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }
        int start = 0;
        int end = 0;

        while (end<s.length()){
            while(!tMap.containsKey(s.charAt(start))){
                start++;
            }
            end = start;
            while (!compare(sMap, tMap) && end < s.length()){{
                sMap.put(s.charAt(end), sMap.getOrDefault(s.charAt(end), 0) + 1);
                end++;
            }}
            while(compare(sMap, tMap)){
                if(end - start < minLength){
                    minLength = end - start + 1;
                    System.out.println(start);
                    System.out.println(end);
                    minSubString = s.substring(start, end);
                    System.out.println(minSubString);
                }
                char startChar = s.charAt(start);
                sMap.put(startChar, sMap.get(startChar) - 1);
                if(sMap.get(startChar)<=0){
                    sMap.remove(startChar);
                }
                start++;
            }
            end++;
        }

        return minSubString;
    }

    public boolean compare(Map<Character, Integer> sMap, Map<Character, Integer> tMap){
        System.out.println(sMap);
        System.out.println(tMap);
        for(Map.Entry<Character, Integer> entry : tMap.entrySet()){
            if(!sMap.containsKey(entry.getKey()) || sMap.get(entry.getKey()) < entry.getValue()){
                return false;
            }
        }
        return true;
    }
}

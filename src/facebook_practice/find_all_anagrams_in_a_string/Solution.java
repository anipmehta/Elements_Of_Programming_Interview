package facebook_practice.find_all_anagrams_in_a_string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
        System.out.println(solution.findAnagrams("abab", "ab"));
    }

    /**
     * @param s String S
     * @param p search string
     * @return List of indices of p (anagram) in S
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        Map<Character, Integer> pMap = new HashMap<>();
        for (char ch : p.toCharArray()) {
            pMap.put(ch, pMap.getOrDefault(ch, 0) + 1);
        }
        int start = 0;
        int end = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (end < p.length() && end < s.length()) {
            window.put(s.charAt(end), window.getOrDefault(s.charAt(end), 0) + 1);
            end++;
        }
        while (end < s.length()) {
//            end++;
            if (isAnagram(window, pMap)) {
//                System.out.println(window);
                list.add(start);
            }
            // slide
            if (window.get(s.charAt(start)) == 1) {
                window.remove(s.charAt(start));
            }
            else{
                window.put(s.charAt(start), window.get(s.charAt(start)) - 1);
            }
            start++;
//            System.out.println(s.charAt(end));
            window.put(s.charAt(end), window.getOrDefault(s.charAt(end), 0) + 1);
            end++;
        }
        // check if the last window is anagram
        if (isAnagram(window, pMap)) {
            list.add(start);
        }
        return list;
    }

    public boolean isAnagram(Map<Character, Integer> window, Map<Character, Integer> pMap) {
        for (char ch : pMap.keySet()) {
            if (!window.containsKey(ch) || window.get(ch) != pMap.get(ch)) {
                return false;
            }
        }
        return pMap.keySet().size() == window.keySet().size();
    }
}

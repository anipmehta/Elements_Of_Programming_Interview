package microsoft_preparation.min_deletions_to_make_char_freq_unique;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public static void main(String [] args){
        Solution solution = new Solution();
        System.out.println(solution.minDeletions("aaabbbcc"));
        System.out.println(solution.minDeletions("ceabaacb"));
        System.out.println(solution.minDeletions("aab"));
    }
    public int minDeletions(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for(char ch : s.toCharArray()){
        map.put(ch, map.getOrDefault(ch, 0) + 1);
    }
    Set<Integer> freq = new HashSet<>();
    int del = 0;
    for(char ch : map.keySet()){
        int count = map.get(ch);
        while(freq.contains(count) && count > 0){
            del++;
            count--;
        }
        if(count!=0){
            freq.add(count);
        }
    }
    return del;
    }
}

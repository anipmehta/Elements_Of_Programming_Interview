package min_deletions_to_make_frequency_of_each_letter_unique;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(minDeletions("aabbffddeaee"));
        Queue<Long> pq = new PriorityQueue<>();
    }
    public static int minDeletions(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch :  s.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0)  + 1);
        }
        List<Integer> frequencies = new ArrayList<>(map.values());
        Collections.sort(frequencies);
        int deleted = 0;
        Set<Integer> set = new HashSet<>();
        for(int freq : frequencies){
            if(!set.contains(freq)){
                set.add(freq);
                continue;
            }
            while(set.contains(freq)){
                deleted++;
                freq--;
            }
            if(freq!=0){
                set.add(freq);
            }
        }
        return deleted;
    }
}

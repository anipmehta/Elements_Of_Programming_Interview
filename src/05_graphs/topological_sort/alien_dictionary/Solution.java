package alien_dictionary;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        String [] words = new String[] {"wrt","wrf","er","ett","rftt"};
        Solution solution = new Solution();
        System.out.println(solution.alienOrder(words));
    }

    public String alienOrder(String[] words) {
        Map<Character, List<Character>> map = new HashMap<>();
        Set<Character> seen = new HashSet<>();
        for(int i=1;i<words.length;i++){
            int firstDiff = getDiff(words[i], words[i-1]);
            System.out.println(firstDiff);
            if(firstDiff > words[i].length()-1 && firstDiff < words[i-1].length()-1){
                return "";
            }
            char key =  words[i-1].charAt(firstDiff);
            char value = words[i].charAt(firstDiff);
            seen.add(key);
            seen.add(value);
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(value);
        }
        System.out.println(map);
        String ans = "";
        Set<Character> visited = new HashSet<>();
        for(Character ch : map.keySet()){
//            System.out.println(ans);
            ans += new StringBuilder(helper(map, visited, ch, ""));
        }
        return ans;
    }

    private int getDiff(String word1, String word2){
        int index = 0;
        while(index < word1.length() - 1 && index < word2.length() -1 && word1.charAt(index)==word2.charAt(index)){
            index++;
        }
        return index;
    }

    private String helper(Map<Character, List<Character>> map, Set<Character> visited, Character ch, String curr) {
        if (!map.containsKey(ch)) {
            return String.valueOf(ch);
        }
        if(visited.contains(ch)){
            return "";
        }
        visited.add(ch);
        curr = ch + curr;
        for(char letter : map.get(ch)){
            String ans = helper(map, visited, letter, curr);
            if(ans==""){
                visited.remove(letter);
            }
            else{
                return letter + ans;
            }
        }
        return curr;
    }
}

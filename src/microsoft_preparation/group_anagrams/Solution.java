package microsoft_preparation.group_anagrams;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> stringToAnagramMap = new HashMap<>();
        for(String str  : strs){
            String sorted = sortString(str);
            if(!stringToAnagramMap.containsKey(sorted)){
                stringToAnagramMap.put(sorted, new ArrayList<>());
            }
            stringToAnagramMap.get(sorted).add(str);
        }
        return stringToAnagramMap.values().stream().collect(Collectors.toList());
    }

    public String sortString(String str){
        char [] arr = str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}

package before_after_puzzle_clutter;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        Solution solution = new Solution();

        System.out.println(solution.secondToLast("APPLE"));
    }

    public List<String> beforeAfterPuzzle(String[] phrases) {
        Map<String, List<Integer>> firstWordMap = new HashMap<>();
        Map<String, List<Integer>> lastWordMap = new HashMap<>();
        Map<String, List<String>> phraseToWord = new HashMap<>();
        TreeSet<String> treeSet = new TreeSet<>();
        for(int i=0;i<phrases.length;i++){
            String [] words = phrases[i].split(" ");
            phraseToWord.put(phrases[i], Arrays.asList(phrases[i].split(" ")));
            String firstWord = words[0];
            String lastWord = words[words.length-1];
            if(!firstWordMap.containsKey(firstWord)){
                firstWordMap.put(firstWord, new ArrayList<>());
            }
            if(!lastWordMap.containsKey(lastWord)){
                lastWordMap.put(lastWord, new ArrayList<>());
            }
            firstWordMap.get(firstWord).add(i);
            lastWordMap.get(lastWord).add(i);
        }
        for (Map.Entry<String, List<Integer>> entry : lastWordMap.entrySet()){
            if(firstWordMap.containsKey(entry.getKey())){
                for(int i : entry.getValue()){
                    for(int j : firstWordMap.get(entry.getKey())){
                        if(i==j) continue;
                        treeSet.add(phrases[i] + phrases[j].replaceFirst(entry.getKey(), ""));
                    }
                }
            }
        }
    return new ArrayList<>(treeSet);
    }
    public String secondToLast(String string) {
        int length = string.length();
        return new String(string.charAt(length-1) + " " + string.charAt(length - 2));
    }


//    public
}

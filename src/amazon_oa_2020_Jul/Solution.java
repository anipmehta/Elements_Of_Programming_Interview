package amazon_oa_2020_Jul;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        System.out.println(retrieveMostFrequentlyUsedWords("Hello blah hello blah", new ArrayList<>()));
    }

    public static List<String> retrieveMostFrequentlyUsedWords(String helpText, List<String> wordsToExclude) {
        List<String> mostFrequent = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        Map<String, Integer> wordCount = new HashMap<>();
        Set<String> excludedSet = new HashSet<>();
        for (String exclude : wordsToExclude) {
            excludedSet.add(exclude.toLowerCase());
        }

        for (String word : helpText.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split(" ")) {
            word = word.toLowerCase();
            if (!excludedSet.contains(word)) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                max = Math.max(max, wordCount.get(word));
            }
        }

        for (String word : wordCount.keySet()) {
            if (wordCount.get(word) == max) {
                mostFrequent.add(word);
            }
        }
        return mostFrequent;
    }
}

package word_break_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String [] args){
        Solution solution = new Solution();
        List<String> dict = new ArrayList<>();
        String s = "catsanddog";
//        "cat","cats","and","sand","dog"
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        System.out.println(solution.wordBreak(s,dict));
    }
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = wordDict.stream().collect(Collectors.toSet());
        List<String> result = new ArrayList<>();
        helper(s, 0, dict, "", result);
        return result;
    }

    public void helper(String s, int index, Set<String> dict, String curr, List<String> result){
        // base case
        if(index > s.length()-1){
            return;
        }
        // base case if solution found
        if(index == s.length()-1){
            if(dict.contains(s)){
                result.add(curr == "" ? s : curr + " " + s);
            }
            return;
        }
        //else recurrce
        String str = s.substring(0, index+1);
        if(dict.contains(str)){
            helper(s.substring(index+1), 0, dict, curr == "" ? str : curr + " " + str, result);
        }
        helper(s, index+1, dict, curr, result);
    }
}

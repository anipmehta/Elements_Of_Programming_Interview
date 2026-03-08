package remove_invalid_parenthesis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String [] args){
        Solution solution = new Solution();
        String s = "()())()";
//        String s = "()()()()()";
        System.out.println(solution.removeInvalidParentheses(s));
    }
    int minRemoveCount = Integer.MAX_VALUE;
    Set<String> validStrings = new HashSet<>();
    /*
    S = "()())()"
     */

    public List<String> removeInvalidParentheses(String s) {
        helper(s, 0,0, 0, 0);
        return validStrings.stream().collect(Collectors.toList());
    }

    private void helper(String s, int index, int lCount, int rCount, int removeCount){
        if(index == s.length()){
            if(lCount!=rCount){
                return;
            }
            if(removeCount<minRemoveCount){
                minRemoveCount  = removeCount;
                validStrings = new HashSet<>();
            }
            if(removeCount==minRemoveCount){
                validStrings.add(s);
            }
            return;
        }
        if(s.charAt(index)!= '(' && s.charAt(index) != ')'){
            helper(s, index+1, lCount, rCount, removeCount);
        }
        String removedString = s.substring(0, index) + s.substring(index+1);
        if(s.charAt(index) == '('){
            helper(s, index+1, lCount+1, rCount, removeCount);
        }
        if(s.charAt(index) == ')' && rCount < lCount){
            helper(s, index + 1, lCount, rCount + 1, removeCount);
        }
        helper(removedString, index, lCount, rCount, removeCount+1);
    }
}

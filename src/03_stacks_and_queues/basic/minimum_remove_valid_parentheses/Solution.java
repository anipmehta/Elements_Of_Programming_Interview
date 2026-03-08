package minimum_remove_valid_parentheses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        Solution solution = new Solution();
        String s = "a)b(c)d";
        System.out.println(solution.minRemoveToMakeValid(s));
    }
    public String minRemoveToMakeValid(String s) {
        StringBuilder leftPass = helper(s, '(', ')');
        StringBuilder rightPass = helper(leftPass.reverse().toString(), ')', '(');
        return rightPass.reverse().toString();
    }

    public StringBuilder helper(String s, char open, char close){
        Set<Integer> removal = new HashSet<>();
        int count = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == close){
                if(count<=0){
                    removal.add(i);
                    continue;
                }
                count--;
            }
            else if(s.charAt(i) == open){
                count++;
            }
        }
        StringBuilder result = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(removal.contains(i)){
                continue;
            }
            result.append(s.charAt(i));
        }
        return result;
    }
}

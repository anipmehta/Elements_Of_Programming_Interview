package microsoft_preparation.transform_ab_ba_msft_ots;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Solution {
    public static void main(String [] args){
//        char ch = 'a'+ 1;
//        System.out.println(ch);
        Solution solution = new Solution();
        System.out.println(solution.solution("CBACD"));
        System.out.println(solution.solution("CABABD"));
        System.out.println(solution.solution("ABABDBC"));
    }

    public String solution(String S) {
        Stack<Character> stack = new Stack<>();
        Set<String> combinations  = new HashSet<>();
        combinations.add("AB");
        combinations.add("BA");
        combinations.add("CD");
        combinations.add("DC");
        if(S.length()==0) {
            return "";
        }
        stack.push(S.charAt(0));
        for (int i=1;i<S.length();i++){
            if(!stack.isEmpty()){
                char top = stack.peek();
                String temp  = new String("" + top + S.charAt(i));
                if(combinations.contains(temp)){
                    stack.pop();
                    continue;
                }
            }
                stack.push(S.charAt(i));
        }
        StringBuilder stringBuilder = new StringBuilder("");
        while (!stack.isEmpty()){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.reverse().toString();
    }
}

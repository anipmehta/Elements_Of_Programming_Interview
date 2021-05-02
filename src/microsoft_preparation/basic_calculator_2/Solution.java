package microsoft_preparation.basic_calculator_2;

import java.util.Stack;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        Solution solution = new Solution();
        System.out.println(solution.calculate("3*2+2"));
    }

    public int calculate(String s) {
        Stack<Integer> operand = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '+' ||  ch == '-'){
                int num =  s.charAt(i + 1) - '0';
                operand.push(ch == '+' ? num : -num);
            }
            else if(ch == '*'){
                int a = operand.pop();
                int b = s.charAt(i+1) - '0';
                operand.push(a*b);
                i = i+2;
            }
            else if(ch == '/'){
                int a = operand.pop();
                int b = s.charAt(i+1) - '0';
                operand.push(a/b);
                i = i+2;
            }
            else {
                operand.push(ch - '0');
            }
        }
        System.out.println(operand);
        int result = 0;
        while (!operand.isEmpty()){
            result += operand.pop();
        }
        return result;
    }
}

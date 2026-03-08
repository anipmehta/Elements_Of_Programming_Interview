package microsoft_preparation.simplify_path;

import java.util.HashSet;
import java.util.Stack;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public String simplifyPath(String path) {
        String [] dirs = path.split("/");
        Stack<String> stack =  new Stack<>();
        for(String dir  : dirs) {
            if(dir.equals("") || dir.equals(".")){
                continue;
            }
            if(dir.equals("..")) {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }
            else{
               stack.push(dir);
            }
        }
        StringBuilder absolutePath = new StringBuilder("");
        for(String dir : stack) {
            absolutePath.append("/");
            absolutePath.append(dir);
        }
        return absolutePath.length() == 0 ? "/" : absolutePath.toString();
    }
}

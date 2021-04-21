package facebook_practice.remove_comments;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        boolean inBlock = false;
        StringBuilder newLine = new StringBuilder();
        for(String src : source){
            int i = 0;
            // important to only set if there is no multi line comment
            if(!inBlock){
                newLine = new StringBuilder();
            }
            while (i<src.length()){
                // check start of block comment
                if(!inBlock && i+1 < src.length() && src.charAt(i) == '/' && src.charAt(i+1) == '*'){
                    inBlock  = true;
                    i++;
                }
                // check end of block comment
                else if(inBlock && i+1 < src.length() && src.charAt(i) == '*' && src.charAt(i+1) == '/'){
                    inBlock = false;
                    i++;
                }
                // check for inline comment
                else if(!inBlock && i+1 < src.length() && src.charAt(i) == '/' && src.charAt(i) == '/'){
                    break;
                }
                //if not in block add char to newLine
                else if(!inBlock){
                    newLine.append(src.charAt(i));
                }
                i++;
            }
            if(!inBlock && newLine.length() > 0) {
                res.add(newLine.toString());
            }
        }
        return res;
    }
}

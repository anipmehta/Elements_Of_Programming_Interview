package unique_letter_string_msft;

import java.util.Arrays;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        Solution solution = new Solution();
        System.out.println(solution.solution("ACAX"));
        System.out.println(solution.solution("CODILITY"));
    }
    public int solution(String S) {
        // write your code in Java SE 8
        int [][] charToLastTwoSeen = new int[26][2];
        for(int i=0;i<charToLastTwoSeen.length;i++) {
            Arrays.fill(charToLastTwoSeen[i], -1);
        }
        int waysToMakeCharUnique = 0;
        int denominator = (int) (Math.pow(10,9) + 7);
        for(int i=0;i<S.length();i++) {
            int ch = S.charAt(i) - 'A';
            int lastIndex = charToLastTwoSeen[ch][1];
            int secondLastIndex = charToLastTwoSeen[ch][0];
            waysToMakeCharUnique = (waysToMakeCharUnique + ((i - lastIndex) * (lastIndex - secondLastIndex) % denominator)) % denominator;
            charToLastTwoSeen[ch][0] = lastIndex;
            charToLastTwoSeen[ch][1] = i;
        }
        for(int i=0;i<26;i++) {
            int lastIndex = charToLastTwoSeen[i][1];
            int secondLastIndex = charToLastTwoSeen[i][0];
            waysToMakeCharUnique = (waysToMakeCharUnique + (S.length() - lastIndex) * (lastIndex - secondLastIndex) % denominator) % denominator;
        }
        return waysToMakeCharUnique;
    }
}

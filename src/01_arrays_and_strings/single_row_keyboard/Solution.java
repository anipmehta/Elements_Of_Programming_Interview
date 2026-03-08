package single_row_keyboard;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String [] args){
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = "abcdefghijklmnopqrstuvwxyz";
        Solution solution = new Solution();
        System.out.println(solution.calculateTime(keyboard, word));
    }

    public int calculateTime(String keyboard, String word) {
        final Map<Character, Integer> keyboardIndexMap = new HashMap<>();
        int totalSum = 0;
        int target = 0;
        char initial = keyboard.charAt(0);
        for(char ch : word.toCharArray()){
            totalSum += Math.abs((ch-initial) - target);
            target = ch-initial;
        }
        return totalSum;
    }
}

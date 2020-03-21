package microsoft_preparation.word_search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(helper(board, word, i, j, new HashSet<String>())){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean helper(char[][] board, String word, int i, int j, Set<String> visited) {
        if(word.length()==0){
            return true;
        }

        if(i < 0 || i > board.length - 1 || j < 0 || j > board[i].length - 1){
            return false;
        }

        if(board[i][j]!=word.charAt(0)){
            return false;
        }
        int [] location = {i,j};
        if(visited.contains(Arrays.toString(location))){
            return false;
        }
        visited.add(Arrays.toString(location));
        String newWord = word.substring(1);
        boolean result =  helper(board, newWord, i+1,j, visited) ||
                helper(board, newWord, i-1, j, visited) ||
                helper(board, newWord, i, j-1, visited) ||
                helper(board, newWord, i, j+1, visited);
        visited.remove(Arrays.toString(location));
        return result;
    }
}

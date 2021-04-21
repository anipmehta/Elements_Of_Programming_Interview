package facebook_practice.knight_dialer;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public int knightDialer(int n) {
       int totalCount = 0;
       int [][] board = new int[4][3];
       Map<String, Integer> cache = new HashMap<>();
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                if(i==3 && (j==0 || j==2)){
                    continue;
                }
                totalCount += helper(n-1,board, i, j, cache);
            }
       }
        return totalCount;
    }
    public int helper(int n, int [][] board, int row, int col, Map<String, Integer> cache){
        if(n==0){
            return 1;
        }
        if(row<0 || col<0 || row>board.length-1 || col > board[0].length-1){
            return 0;
        }
        if(row == board.length - 1 && (col == 0 || col == board[0].length-1)){
            return 0;
        }
        int [] newX = new int[]{-2, -2 , 2, 2, -1, 1, -1, 1};
        int [] newY = new int[]{-1, 1, -1, 1, -2, -2, 2, 2};
        String key =  row + ":" + col + ":" + n;
        if(!cache.containsKey(key)){
            int totalCount = 0;
            for(int i=0;i<newX.length;i++){
               totalCount += helper(n-1, board, row + newX[i], col + newY[i], cache);
            }
            cache.put(key , totalCount);
        }

        return cache.get(key);
    }
}

package microsoft_preparation.n_queens;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<List<int[]>> ways = new ArrayList<>();
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    /*
        [, _1, _, _]
        [_, , _,  1]
        [1, , _, _]
        [, _, 1, _]
     */
    /*
    Pseudo:
        start with [0,0]
            if canPlace()
                place();
                if recurse(row+1, board, n-1):
                    add(ans)
                // backtrack
                unplace()



     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        int [][] board = new int[n][n];
        //boolean [][] visited = new int[n][n];

        helper(board, 0, n, new ArrayList<>());
        for(List<int []> way : ways){
            for(int [] pos : way){
                String rowString = "";

                List<String> ans =
                results.add(ans);
            }
        }



    }

    public boolean helper(int [][] board, int row, int n, List<int[]> positions){
        if(n==0){
            ways.add(positions);
            return true;
        }
        for(int i=0;i<n;i++){
            if(!canPlace(row, i, board)){
                return false;
            }
            board[row][i] = 1;
            int [] pos = new int[]{row, i};
            positions.add(pos);
            if(helper(board, row + 1, n-1, positions)){
                return true;
            }
            //backtrack
            positions.remove(pos);
            board[row][i] = 0;
        }
        return false;
    }

    private boolean canPlace(int row, int col, int [][] board) {
        return true;
    }
}

package robot_in_a_maze;

import java.util.Arrays;

public class Solution {
    public static void main(String [] args){
        System.out.println(countWays(3, 4));
    }
    public static int countWays(int m, int n){
        int [][] cache = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(cache[i], -1);
        }
        int totalWays = helper(m, n, m-1, 0, cache);
        //Printing cache
        for(int i=-0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(cache[i][j] + " ");
            }
            System.out.println();
        }
        return totalWays;
    }
    public static int helper(int m, int n, int row, int col, int [][] cache){
        if(row>m-1 || row<0 || col<0 || col>n-1){
            return 0;
        }
        if(cache[row][col]==-1){
            cache[row][col] =  helper(m, n,  row, col+1, cache) + helper(m, n, row-1, col+1, cache) +
                    helper(m, n, row+1, col+1, cache);
        }
        if(row==m-1 && col==n-1){
            cache[row][col] = 1;
            return 1;
        }
        return cache[row][col];
    }
}

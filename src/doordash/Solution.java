package doordash;

import java.util.List;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        Solution solution = new Solution();
        int [][] grid = new int[][]{
                {1,1,1,2,4},
                {5,1,5,3,1},
                {3,4,2,1,1}
        };
        int [][] grid2 = new int[][]{
                {1,1},
                {5,5},
                {5,5}
        };
        int [][] grid3 = new int[][]{
                {9, 9, 9, 9, 9, 9, 9},
                {9, 9, 8, 9, 9, 9, 9},
                {9, 9, 9, 12, 9, 9, 9},
                {9, 9, 9, 12, 9, 9, 9},
                {9, 9, 9, 12, 9, 9, 9},
                {9, 9, 9, 12, 9, 9, 9}
        };
        int [][] grid4 = new int[][]{
                {1,1,1,2,4},
                {5,1,5,3,1},
                {3,4,2,1,1}
        };
        System.out.println(solution.longestPath(grid));
        System.out.println(solution.longestPath(grid2));
        System.out.println(solution.longestPath(grid3));
        System.out.println(solution.longestPath(grid4));
    }

    public int longestPath(int [][] grid) {
        int max = Integer.MIN_VALUE;
        boolean [][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                max = Math.max(dfs(grid, i, j, grid[i][j], visited, 0), max);
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int i, int j, int target, boolean[][] visited, int path) {
        if(i<0 || j<0 || i > grid.length-1 || j> grid[i].length-1 ||
                visited[i][j] || grid[i][j]!=target){
            return 0;
        }
        visited[i][j] = true;
        int [] X = new int[]{-1,1, 0,0};
        int [] Y = new int[]{0,0,-1,1};
        int count = 0;
        for(int k=0;k<X.length;k++){
            count+= dfs(grid, i + X[k], j + Y[k], target, visited, path+1);
        }
        return count+1;
    }

    class Tile{
        int val;
        List<Tile> neighbours;
    }
}

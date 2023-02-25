package warner_bros_discovery.largest_perimeter;

public class Solution {
    public static void main(String [] args){
        int [][] grid = new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 1, 1},
        };
        int [][] grid2 = new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 0},
                {0, 1, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };


        System.out.println(maxPerimeter(grid));
        System.out.println(maxPerimeter(grid2));
    }

    public static int maxPerimeter(int [][] grid){
        int maxPerimeter = Integer.MIN_VALUE;
        boolean [][] visited = new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]==1){
                    maxPerimeter = Math.max(maxPerimeter, dfs(grid, i, j, visited));
                }
            }
        }
        return maxPerimeter;
    }

    public static int dfs(int [][] grid, int row, int col, boolean [][]visited){
        if(row < 0 || col < 0 || row > grid.length - 1 || col > grid[row].length - 1){
            return 0;
        }
        if(visited[row][col] || grid[row][col] == 0){
            return 0;
        }
        visited[row][col] = true;
        int [] x = new int[]{0,0,-1,1};
        int [] y = new int[]{-1,1,0,0,};
        int size = isEdge(grid, row, col) ? 1 : 0;
        for(int i=0;i<x.length;i++){
            size += dfs(grid, row + x[i], col + y[i], visited);
        }
        return size;
    }

    public static boolean isEdge(int [][] grid, int row, int col){
        if(col -1 < 0 || col + 1 > grid[row].length -1 || row - 1 < 0 || row + 1 > grid.length - 1){
            return true;
        }
        return grid[row + 1][col] == 0 ||
                grid[row - 1][col] == 0 ||
                grid[row][col + 1] == 0 ||
                grid[row][col - 1] == 0;
    }
}

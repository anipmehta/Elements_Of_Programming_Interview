package island_perimeter;

import javafx.scene.control.cell.CheckBoxListCell;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    public int islandPerimeter(int[][] grid) throws Exception {
        boolean [][] visited = new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0; j< grid[i].length;j++){
                if(grid[i][j]==1){
                    return helper(grid, visited, i, j);
                }
            }
        }
        throw new Exception("No Island Found");
    }

    private int helper(int[][] grid, boolean[][] visited, int row, int col) {
        if(row < 0 || col < 0 || row > grid.length-1 || col > grid[row].length - 1){
            return 0;
        }
        if(visited[row][col]){
            return 0;
        }
        if(grid[row][col]==0){
            return 1;
        }
        visited[row][col] = true;
        int [] X = new int[]{0,0,1,-1};
        int [] Y = new int[]{1,-1,0,1};
        int perimeter = 0;
        if(row-1 <0 || row+1 > grid.length - 1){
            perimeter += 1;
        }
        if(col -1<0 || col + 1 > grid.length - 1){
            perimeter += 1;
        }
        for(int i=0;i<X.length;i++){
            int newX = row + X[i];
            int newY = row + Y[i];
            perimeter += helper(grid, visited, newX, newY);
        }
        return perimeter;
     }
}

package paint_a_boolean_matrix;

import util.Coordinate;
import util.GridHelper;

public class Solution {
    public static void main(String [] args){
        Integer [][]A = {{0,0,1},
                     {1,0,0},
                     {0,1,1}};
        System.out.println("I/P:");
        GridHelper.printGrid(A);
        paintMatrix(A, new Coordinate(0,1));
        System.out.println("O/P:");
        GridHelper.printGrid(A);
    }
    public static void paintMatrix(Integer[][] grid, Coordinate coordinate){
        int currColor = grid[coordinate.row][coordinate.col];
        dfs(grid, coordinate, currColor);
    }
    public static void dfs(Integer[][] grid, Coordinate start, int color){
        if(start.row < 0 || start.col < 0 || start.row > grid.length-1 || start.col > grid[start.row].length-1){
            return;
        }
        if(grid[start.row][start.col]!=color){
            return;
        }
        //mark as visited and change color
        grid[start.row][start.col] = 1 - color;
        int [] nextX = {0,0,1,-1};
        int [] nextY = {-1,1,0,0};
        for(int i=0;i<nextX.length;i++){
            dfs(grid, new Coordinate(start.row + nextX[i], start.col + nextY[i]), color);
        }
    }
}

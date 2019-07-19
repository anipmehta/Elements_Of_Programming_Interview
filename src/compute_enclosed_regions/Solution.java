package compute_enclosed_regions;

import util.Coordinate;
import util.GridHelper;

public class Solution {
    public static void main(String [] args){
        Character [][] grid =
                {{'B', 'B', 'B', 'B'},
                 {'W', 'B', 'W', 'B'},
                 {'B', 'W', 'W', 'B'},
                 {'B', 'B', 'B', 'B'}};
        System.out.println("I/P:");
        GridHelper.printGrid(grid);
        changeColorOfBoundedCells(grid);
        System.out.println("O/P:");
        GridHelper.printGrid(grid);
    }
    public static void changeColorOfBoundedCells(Character[][] A){
        //check first col and last col
        for(int i=0;i<A.length;i++){
            dfs(A, new Coordinate(i, 0));
            dfs(A, new Coordinate(i, A[i].length-1));
        }
        //check first row and last row
        for(int i=0;i<A.length;i++){
            dfs(A, new Coordinate(0, i));
            dfs(A, new Coordinate(0, A.length-1));
        }
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[i].length;j++){
                A[i][j] = A[i][j] != 'T' ? 'B' : 'W';
            }
        }
    }
    public static void dfs(Character[][] A, Coordinate coordinate){
        if(!coordinate.isValid(A)){
            return;
        }
        if(A[coordinate.row][coordinate.col]!='W'){
            return;
        }
        A[coordinate.row][coordinate.col] = 'T';
        for(Coordinate coord : coordinate.getNeighbours(4)){
            dfs(A, coord);
        }
    }
}

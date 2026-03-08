package compute_enclosed_regions;

import util.Coordinate;
import util.MatrixCoordinate;
import util.GridHelper;

import java.util.List;

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
            dfs(A, new MatrixCoordinate(i, 0));
            dfs(A, new MatrixCoordinate(i, A[i].length-1));
        }
        //check first row and last row
        for(int i=0;i<A.length;i++){
            dfs(A, new MatrixCoordinate(0, i));
            dfs(A, new MatrixCoordinate(0, A.length-1));
        }
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[i].length;j++){
                A[i][j] = A[i][j] != 'T' ? 'B' : 'W';
            }
        }
    }
    public static void dfs(Character[][] A, Coordinate coordinate){
        MatrixCoordinate matrixCoordinate = (MatrixCoordinate) coordinate;
        if(!coordinate.isValid(A)){
            return;
        }
        if(A[matrixCoordinate.row][matrixCoordinate.col]!='W'){
            return;
        }
        A[matrixCoordinate.row][matrixCoordinate.col] = 'T';
        for(Coordinate coord :  coordinate.getNeighbours(4)){
            dfs(A, coord);
        }
    }
}

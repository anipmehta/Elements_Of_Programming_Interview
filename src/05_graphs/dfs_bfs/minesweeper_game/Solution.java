package minesweeper_game;

import util.Coordinate;
import util.GridHelper;
import util.MatrixCoordinate;

import java.util.List;

public class Solution {
    public static void main(String [] args){
        Character [][] board =
                {{'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}};
        int [] point = {3,0};
        System.out.println("I/P");
        GridHelper.printGrid(board);
        updateBoard(board, point);
        System.out.println("O/P");
        GridHelper.printGrid(board);
        int [] pt2 = {1,2};
        System.out.println("State 2");
        updateBoard(board, pt2);
        GridHelper.printGrid(board);
    }
    public static Character[][] updateBoard(Character[][] board, int [] click){
        MatrixCoordinate clickCoordinate = new MatrixCoordinate(click[0], click[1]);
        dfs(board, clickCoordinate);
        return board;
    }
    public static void dfs(Character [][] board, Coordinate point){
        MatrixCoordinate start = (MatrixCoordinate) point;
        if(!start.isValid(board)){
            return;
        }
        if(board[start.row][start.col]=='M'){
            board[start.row][start.col] = 'X';
            return;
        }
        if(board[start.row][start.col]=='E'){
            int getMines = countMines(board, start);
            if(getMines!=0){
                board[start.row][start.col] = String.valueOf(getMines).charAt(0);
                return;
            }
            board[start.row][start.col] = 'B';
            for(Coordinate coordinate : start.getNeighbours(8)){
                dfs(board, coordinate);
            }
        }
    }

    private static int countMines(Character[][] board, MatrixCoordinate start) {
        List<Coordinate> neighbours =  start.getNeighbours(8);
        int count = 0;
        for(int i=0;i<start.getNeighbours(8).size();i++){
            MatrixCoordinate coordinate = (MatrixCoordinate) neighbours.get(i);
            if(coordinate.isValid(board) && board[coordinate.row][coordinate.col]=='M'){
                count++;
            }
        }
        return count;
    }
}

package tic_tac_toe;

import util.GridHelper;
import java.util.Random;
import java.util.Scanner;

public class Solution {
    private Character player;
    private Character [][] board;
    Random random;
    int size;
    public int movesCount;
    public Solution(int n, Character player){
        board = new Character[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                board[i][j] = '-';
            }
        }
        random = new Random();
        size = n;
        movesCount = 0;
        this.player = player;
    }
    public int [] getEmptySpace(){
        return getRandomPosition();
    }
    public int getRandomIndex(){
        return random.nextInt(size*size);
    }
    public int [] getRandomPosition(){
        int index = getRandomIndex();
        int row = index/size;
        int col = index%size;
        while(!checkIsEmpty(row, col)){
            index = getRandomIndex();
            row = index/size;
            col = index%size;
        }
        return new int[]{row, col};
    }
    public Character[][] getBoard(){
        return board;
    }
    public boolean makeMove(Character player, int row, int col){
        if(board[row][col]== 'X' || board[row][col]=='O'){
            return false;
        }
        board[row][col] = player;
        movesCount++;
        return true;
    }

    public boolean checkIsEmpty(int row, int col){
        return !(board[row][col]=='X' || board[row][col]=='O');
    }
    public boolean hasGameEnded(){
        return movesCount==size*size;
    }
    public boolean hasPlayerWon(Character player){
        for(int i=0;i<size;i++){
            if(checkRow(i, player) || checkCol(i, player)){
                return true;
            }
        }
        if(checkDiagonal1(player) || checkDiagonal2(player)){
            return true;
        }
        return false;
    }
    private boolean checkDiagonal1(Character player){
        for(int i=0;i<size;i++){
            if(board[i][i]!=player){
                return false;
            }
        }
        return true;
    }
    private boolean checkDiagonal2(Character player){
        for(int i=0;i<size;i++){
            if(board[i][size-i-1]!=player){
                return false;
            }
        }
        return true;
    }
    private boolean checkCol(int i, Character player) {
        for(int j=0;j<size;j++){
            if(board[j][i]!=player){
                return false;
            }
        }
        return true;
    }

    private boolean checkRow(int i, Character player) {
        for(int j=0;j<size;j++){
            if(board[i][j]!=player){
                return false;
            }
        }
        return true;
    }

    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose size of board");
        int size = scanner.nextInt();
        System.out.println("Please choose your move. Enter X or O");
        Character player = scanner.next().charAt(0);
        Solution game = new Solution(size, player);
        while (true){
            int row;
            int col;
            if(game.getPlayer()==player){
                System.out.println("Enter your move's row");
                row = scanner.nextInt();
                System.out.println("Enter your move's col");
                col = scanner.nextInt();
            }
            else{
                System.out.println("Computer is making move.......");
                int [] pos = game.getEmptySpace();
                row = pos[0];
                col = pos[1];
            }

            if(!game.makeMove(game.getPlayer(), row, col)) {
                System.out.println("Invalid Move");
                continue;
            }
            if(game.hasPlayerWon(game.getPlayer())){
                System.out.println("Player Won " + game.getPlayer());
                GridHelper.printGrid(game.getBoard());
                System.exit(1);
            }
            if(game.hasGameEnded()){
                System.out.println("Game Tied");
                GridHelper.printGrid(game.getBoard());
                System.exit(1);
            }
            System.out.println("Move made by player->"+game.getPlayer());
            GridHelper.printGrid(game.getBoard());
            game.player = game.nextPlayer();
        }
    }
    public Character nextPlayer(){
        return player=='X' ? 'O' : 'X';
    }

    public Character getPlayer() {
        return player;
    }
}

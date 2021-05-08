package microsoft_preparation.sudoku_solver;

public class Solution {
    public static void main(String[] args) {
        char ch = 'a' + 1;
        System.out.println(ch);
    }

    /*
        start with first NonEmpty row,col
            loop from i 1 to 9
                if(isSafe(i,row,col)):
                    board[row][col] = i
                    recurse(nextEmptyRow, nextEmptyCol))
                    if noMoreRowCol
                        return
                    board[row][col] = ''



     */
    public void solveSudoku(char[][] board) {
        helper(board);
    }

    public int[] findEmptyCell(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public boolean helper(char[][] board) {
        int[] emptyCell = findEmptyCell(board);
        int row = emptyCell[0];
        int col = emptyCell[1];
        if (row == -1 && col == -1) {
            return true;
        }

        for (int i = 1; i <= 9; i++) {
            if (!isSafe(board, row, col, i)) {
                continue;
            }
            board[row][col] = getChar(i);
            if (helper(board)) {
                return true;
            }
            // backtrack
            board[row][col] = '.';
        }
        return false;
    }

    public char getChar(int n) {
        return Character.forDigit(n, 10);
    }

    public boolean isSafe(char[][] board, int row, int col, int n) {
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] == getChar(n)) {
                return false;
            }
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == getChar(n)) {
                return false;
            }
        }
        int startRow = (row/3)*3;
        int startCol = (col/3)*3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == getChar(n)) {
                    return false;
                }
            }
        }
        return true;
    }
}

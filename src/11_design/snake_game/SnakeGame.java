package microsoft_preparation.snake_game;

import java.util.Deque;
import java.util.LinkedList;

class SnakeGame {
    int [][] grid;
    boolean [][] food;
    Deque<int []> snakeBody;
    int score;
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        grid = new int[height][width];
        this.food = new boolean[height][width];
        for(int [] foodLocation : food) {
            this.food[foodLocation[0]][foodLocation[1]] = true;
        }
        snakeBody = new LinkedList<>();
        int [] pos = {0,0};
        snakeBody.offerFirst(pos);
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) throws Exception {
        int [] pos = nextPosition(direction);
        if(outOfBounds(pos)) {
            return -1;
        }
        if(snakeBodyHasCell(pos)) {
            return -1;
        }
        if(nextCellHasFood(pos)) {
            score++;
            increaseSnakeBody(pos);
            return score;
        }
        moveSnake(pos);
        return score;
    }

    private boolean nextCellHasFood(int[] nextCell) throws Exception {
        return food[nextCell[0]][nextCell[1]];
    }

    private int [] moveSnake(int[] nextCell) throws Exception {
        int [] removed = snakeBody.removeLast();
        snakeBody.addFirst(nextCell);
        return removed;
    }

    private void increaseSnakeBody(int[] nextCell) throws Exception {
        snakeBody.addFirst(nextCell);
    }

    private boolean snakeBodyHasCell(int[] nextCell) throws Exception {
        int index = 0;
        for(int [] cell : snakeBody) {
            if(index!=snakeBody.size()-1 && cell[0]==nextCell[0] && cell[1]==nextCell[1]){
                return true;
            }
            index++;
        }
        return false;
    }

    private int [] nextPosition(String direction) throws Exception {
        int [] head = snakeBody.peekFirst();
        int row = head[0];
        int col = head[1];
        int [] pos;
        char dir = direction.charAt(0);
        switch (dir) {
            case 'U':
                pos = new int[] {row-1,col};
                break;
            case 'D' :
                pos = new int[] {row+1,col};
                break;
            case 'L':
                pos = new int[] {row, col-1};
                break;
            case 'R':
                pos = new int [] {row, col+1};
                break;
            default:
                throw new Exception("Movement Not Supported");
        }
        return pos;
    }

    private boolean outOfBounds(int[] nextCell) throws Exception {
        if(nextCell[0] < 0 || nextCell[0] > grid.length - 1 || nextCell[1] < 0 || nextCell[1] > grid[0].length - 1) {
            return false;
        }
        return true;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */

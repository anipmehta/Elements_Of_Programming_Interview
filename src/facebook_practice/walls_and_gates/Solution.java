package facebook_practice.walls_and_gates;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    class Item{
        int row;
        int col;
        int distance;
        public Item(int row, int col, int distance){
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    public void wallsAndGates(int[][] rooms) {
        for(int i=0;i<rooms.length;i++){
            for(int j=0;j<rooms[0].length;j++){
                if(rooms[i][j] == 0){
                    boolean [][] visited = new boolean[rooms.length][rooms[0].length];
                    updateDistance(rooms, i, j, 0, visited);
                }
            }
        }
    }

    public void wallsAndGatesBFS(int [][] rooms){
        Queue<Item> queue = new LinkedList<>();
        for(int i=0;i<rooms.length;i++){
            for(int j=0;j<rooms.length;j++){
                if(rooms[i][j] == 0){
                    queue.offer(new Item(i, j, 0));
                }
            }
        }
        while (!queue.isEmpty()){
            Item curr = queue.poll();
            int [] X = new int[]{0,0,-1,1};
            int [] Y = new int[]{-1,1,0,0};
            if(curr.row < 0 || curr.col < 0 || curr.row > rooms.length - 1 || curr.col > rooms[0].length - 1){
                continue;
            }
            if(rooms[curr.row][curr.col]!=Integer.MAX_VALUE){
                continue;
            }
            rooms[curr.row][curr.col] = curr.distance;
            for(int i = 0;i<X.length;i++){
                int newX = curr.row + X[i];
                int newY = curr.col + Y[i];
                queue.offer(new Item(newX, newY, curr.distance + 1));
            }
        }
    }

    private void updateDistance(int[][] rooms, int i, int j, int distance, boolean[][] visited) {
        if(i < 0 || j < 0 || i > rooms.length-1 || j > rooms[i].length - 1){
            return;
        }
        if(visited[i][j] || rooms[i][j] == -1 || rooms[i][j] < distance){
            return;
        }
        visited[i][j] = true;
        rooms[i][j] = Math.min(rooms[i][j], distance);
        int [] X = new int[]{0,0,-1,1};
        int [] Y = new int[]{-1,1,0,0};
        for(int k=0;k<X.length;k++){
            updateDistance(rooms, i + X[k], j + Y[k], distance + 1, visited);
        }
    }
}

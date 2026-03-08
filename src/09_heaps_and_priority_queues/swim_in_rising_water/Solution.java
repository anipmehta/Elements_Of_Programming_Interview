package swim_in_rising_water;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    public static void main(String [] args){
//        int [][] grid = {{0,2},
//                         {1,3}};
        int [][] grid = {{0,1,2,3,4},
                         {24,23,22,21,5},
                         {12,13,14,15,16},
                         {11,17,18,19,20},
                         {10,9,8,7,6}};
        System.out.println(swimInWater(grid));
    }
    public static int swimInWater(int [][] grid){
        boolean [][] visited = new boolean[grid.length][grid[0].length];
        Queue<HeapItem> minHeap = new PriorityQueue<>(grid.length * grid[0].length, new Comparator<HeapItem>() {
            @Override
            public int compare(HeapItem o1, HeapItem o2) {
                return o1.value-o2.value;
            }
        });
        int minTime = grid[0][0];
        minHeap.offer(new HeapItem(0, 0, grid[0][0]));
        while(!minHeap.isEmpty()){
            HeapItem top = minHeap.poll();
            int [] nextRows = {0,0,1,-1};
            int [] nextCols = {1,-1,0,0};
            minTime = Math.max(minTime, top.value);
            if(top.row == grid.length - 1 && top.col == grid[0].length - 1){
                break;
            }
            for(int i=0;i<4;i++){
                int newRow = top.row + nextRows[i];
                int newCol = top.col + nextCols[i];
                if(isValidNeighbour(grid, newRow, newCol, visited)){
                    minHeap.offer(new HeapItem(newRow, newCol, grid[newRow][newCol]));
                    visited[newRow][newCol] = true;
                }
            }
        }
        return minTime;
    }

    private static boolean isValidNeighbour(int[][] grid, int newRow, int newCol, boolean [][] visited) {
        return !(newRow < 0 || newRow > grid.length - 1 || newCol < 0 || newCol > grid[newRow].length - 1) &&
                !visited[newRow][newCol];
    }

    static class HeapItem{
        public int row;
        public int col;
        public int value;
        public HeapItem(int row, int col, int value){
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
}

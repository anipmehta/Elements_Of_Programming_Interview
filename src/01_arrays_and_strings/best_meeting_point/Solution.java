package best_meeting_point;

import util.Coordinate;
import util.MatrixCoordinate;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String [] args){
        Integer[][] grid = {{1,0,0,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        System.out.println(minTotalDistance(grid));
    }
    static class BFSNode{
        public int distance;
        Coordinate location;
        public BFSNode(int distance, Coordinate location){
            this.distance = distance;
            this.location = location;
        }
    }
    public static int minTotalDistance(Integer[][] grid) {
        int [][] totalDistanceFromEachHouse = new int[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]==1){
                    Coordinate start = new MatrixCoordinate(i, j);
                    bfs(grid, start, totalDistanceFromEachHouse);
                }
            }
        }
        int min_count = Integer.MAX_VALUE;
        for(int i=0;i<totalDistanceFromEachHouse.length;i++){
            for(int j=0;j<totalDistanceFromEachHouse[i].length;j++){
                if(totalDistanceFromEachHouse[i][j]==0){
                    continue;
                }
                min_count = Math.min(totalDistanceFromEachHouse[i][j], min_count);
            }
        }
        return min_count;
    }

    private static void bfs(Integer[][] grid, Coordinate start, int [][] distancesGrid) {
        Queue<BFSNode> queue = new LinkedList<>();
        BFSNode node = new BFSNode(0, start);
        boolean [][] visited = new boolean[grid.length][grid[0].length];
        queue.offer(node);
        while(!queue.isEmpty()){
            node = queue.poll();
            MatrixCoordinate matrixCoordinate = (MatrixCoordinate) node.location;
            distancesGrid[matrixCoordinate.row][matrixCoordinate.col] += node.distance;
            for(Coordinate coordinate : node.location.getNeighbours(4)){
                MatrixCoordinate nextNeighbour = (MatrixCoordinate) coordinate;
                if(!nextNeighbour.isValid(grid) || grid[nextNeighbour.row][nextNeighbour.col] == 1
                        || visited[nextNeighbour.row][nextNeighbour.col]){
                    continue;
                }
                visited[nextNeighbour.row][nextNeighbour.col] = true;
                BFSNode newNode = new BFSNode(nextNeighbour.getManhattanDistance(start), coordinate);
                queue.offer(newNode);
            }
        }
    }
}

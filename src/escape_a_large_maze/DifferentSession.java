package escape_a_large_maze;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class DifferentSession {
    class Pair{
        public int x;
        public int y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + ":" + y;
        }
    }
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        return bfs(blocked, source, target) && bfs(blocked, target, source);
    }
    public boolean bfs(int[][] blocked, int[] source, int[] target) {
        if(blocked.length == 0){
            return true;
        }
        Queue<Pair> queue = new LinkedList<>();
        Set<String> blockedSet = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for(int i=0;i<blocked.length;i++){
            Pair block = new Pair(blocked[i][0], blocked[i][1]);
            blockedSet.add(block.toString());
        }
        queue.offer(new Pair(source[0], source[1]));
        int [] X = new int[]{0,0,-1,1};
        int [] Y = new int[]{-1,1,0,0};
        while (!queue.isEmpty()){
            Pair pair = queue.poll();
            visited.add(pair.toString());
            if(pair.x == target[0] && pair.y == target[1]){
                return true;
            }
           for(int i=0;i<X.length;i++){
               Pair next = new Pair(pair.x + X[i], pair.y + Y[i]);
               if(!visited.contains(next.toString()) && !blockedSet.contains(next.toString()) &&
                       inGrid(pair.x + X[i], pair.y + Y[i]) ){
                   queue.offer(next);
               }
               // some magic number ??? TODO:// figure out this
               if(queue.size() == 20000){
                   return true;
               }
           }
        }
        return false;
    }
    boolean inGrid(int x, int y){
        return x>=0 && x <= 20000 && y>=0 && y <= 20000;
    }
}

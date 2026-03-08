package escape_a_large_maze;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String [] args){

    }
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<String> visited = new HashSet<>();
        Set<String> blockedSet = new HashSet<>();
        for(int i=0;i<blocked.length;i++){
            blockedSet.add(Arrays.toString(blocked[i]));
        }
        return dfs(source, blockedSet, visited, target);
    }

    private boolean dfs(int[] source, Set<String> blockedSet, Set<String> visited, int[] target) {
        if(source[0]<0 || source[0]>999999 || source[1]<0 || source[1]>999999){
            return false;
        }
        if(visited.contains(Arrays.toString(source)) || blockedSet.contains(Arrays.toString(source))){
            return false;
        }
        if(source[0]==target[0] && source[1]==target[1]){
            return true;
        }
        visited.add(Arrays.toString(source));
        return  dfs(new int[]{source[0], source[1]-1}, blockedSet, visited, target) ||
                dfs(new int[]{source[0], source[1]+1}, blockedSet, visited, target) ||
                dfs(new int[]{source[0]-1, source[1]}, blockedSet, visited, target) ||
                dfs(new int[]{source[0]+1, source[1]}, blockedSet, visited, target);

    }
}

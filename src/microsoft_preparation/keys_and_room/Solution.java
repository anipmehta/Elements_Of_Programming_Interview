package microsoft_preparation.keys_and_room;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        helper(rooms, 0, visited);
        for(int i=0;i<rooms.size();i++){
            if(!visited.contains(i)){
                return false;
            }
        }
        return true;
    }

    private void helper(List<List<Integer>> rooms, int room, Set<Integer> visited) {
        if(visited.contains(room)){
            return;
        }
        visited.add(room);
        for(int keys : rooms.get(room)){
            if(!visited.contains(keys)){
                helper(rooms, keys, visited);
            }
        }
    }
}

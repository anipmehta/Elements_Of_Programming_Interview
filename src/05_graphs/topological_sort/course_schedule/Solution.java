package microsoft_preparation.course_schedule;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int [] relation : prerequisites) {
            int course = relation[0];
            int pre = relation[1];
            if(!graph.containsKey(course)){
                graph.put(course, new ArrayList<>());
            }
            graph.get(course).add(pre);
        }
        Set<Integer> visited = new HashSet<>();
        for(int i=0;i<numCourses;i++){
            if(isCycle(i, graph, visited)){
                return false;
            }
        }
        return true;
    }

    public boolean isCycle(int course, Map<Integer, List<Integer>> graph, Set<Integer> visited){
        if(visited.contains(course)){
            return true;
        }
        if(!graph.containsKey(course)){
            return false;
        }
        visited.add(course);
        boolean result = false;
        for(int pre : graph.get(course)){
            result = isCycle(pre, graph, visited);
            if(result){
                break;
            }
        }
        visited.remove(course);
        return result;

    }
}

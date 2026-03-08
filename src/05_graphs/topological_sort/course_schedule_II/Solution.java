package doordash.course_schedule_II;


import java.util.*;

public class Solution {
    public static void main(){
        Solution solution = new Solution();
        int courses = 1;
        int [][] pre = new int[][]{
                {}
        };
        System.out.println(solution.findOrder(courses, pre));
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = buildGraph(prerequisites, numCourses);
        List<Integer> order = new ArrayList<>();
        int first;
        for(int i=0;i<prerequisites.length;i++){
//            if(!map.c)
        }
        dfs(map, numCourses, 0, order, new HashSet<>());
        int [] res = new int[order.size()];
        int index = 0;
        for(int num : order){
            res[index++] = num;
        }
        return res;
    }

    private void dfs(Map<Integer, List<Integer>> map, int numCourses, int preReq,
                              List<Integer> order,
                              Set<Integer> visited) {
        if(visited.contains(preReq)){
            order = new ArrayList<>();
        }
        order.add(preReq);
        visited.add(preReq);
        if(!map.containsKey(preReq)){
            return;
        }
        for(int course : map.get(preReq)){
            dfs(map, numCourses-1, course, order, visited);
        }
    }

    public Map<Integer, List<Integer>> buildGraph(int [][] prerequisites, int n){
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(i, new ArrayList<>());
        }
        for(int [] pre : prerequisites){
            int course = pre[0];
            int preReq = pre[1];
            map.get(preReq).add(course);
        }
        return map;
    }

    public Map<Integer, Integer> buildInDegree(Map<Integer, List<Integer>> graph, int n){
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(i, 0);
        }
        for(int i=0;i<n;i++){
            for(int next : graph.get(i)){
                map.put(next, map.get(next+1));
            }
        }
        return map;
    }

    public int [] listToArray(List<Integer> list){
        int [] res = new int[list.size()];
        int index = 0;
        for(int num : list){
            res[index++] = num;
        }
        return res;
    }

    public int[] findOrderBFS(int numCourses, int[][] prerequisites){
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites, numCourses);
        Map<Integer, Integer> inDegree = buildInDegree(graph, numCourses);
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : inDegree.entrySet()){
            if(entry.getValue()==0){
                queue.offer(entry.getKey());
            }
        }
        while (!queue.isEmpty()){
            int parent = queue.poll();
            result.add(parent);
            for(int next : graph.get(parent)){
                inDegree.put(next, inDegree.get(next)-1);
                if(inDegree.get(next)==0){
                    queue.offer(next);
                }
            }
        }
        if(queue.size()!=numCourses){
            return new int[]{};
        }
        return listToArray(result);
    }
}

package clutter_classify_shortest_edges_in_weighted_graph;

import java.util.*;

public class Trucking {
    public static boolean is_blocked(List<String> required_tasks, List<String> task_from, List<String> task_to) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> required_tasks_set = new HashSet<>(required_tasks);
        for (int i = 0; i < task_from.size(); i++) {
            String to = task_to.get(i);
            String from = task_from.get(i);
            if (!map.containsKey(to)) {
                map.put(to, new ArrayList<>());
            }
            map.get(to).add(from);
        }
        Set<String> visited = new HashSet<>();
        for(String task : required_tasks) {
            if(!can_be_completed(map, visited, required_tasks_set, task)){
                return true;
            }
        }
        return false;
    }

    public static boolean can_be_completed(Map<String, List<String>> map, Set<String> visited, Set<String> required_task, String task) {
        if(visited.contains(task)){
            return false;
        }
        visited.add(task);
        if (!map.containsKey(task)) {
            return true;
        }
        List<String> before_tasks = map.get(task);
        for (String before_task : before_tasks) {
            if (visited.contains(before_task) || !required_task.contains(before_task)) {
                continue;
            }
            if (!can_be_completed(map, visited, required_task, before_task)) {
                return false;
            }
        }
        return true;
    }
}


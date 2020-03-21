package microsoft_preparation.vertical_order_tree_traversal;

import util.TreeNode;

import java.util.*;

public class Solution {
    private int minLevel = Integer.MAX_VALUE;
    private int maxLevel = Integer.MIN_VALUE;
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public static class Location {
        public int x;
        public int y;
        public int value;
        public Location(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, PriorityQueue<Location>> levelToNodesMap = new HashMap<>();
        helper(root, new Location(0,0, Integer.MIN_VALUE), levelToNodesMap);
        List<List<Integer>> output = new ArrayList<>();
        for(int i=minLevel;i<=maxLevel;i++){
            Queue<Location> levelNodesPQ = levelToNodesMap.get(i);
            List<Integer> levelNodes = new ArrayList<>();
            while (!levelNodesPQ.isEmpty()){
                levelNodes.add(levelNodesPQ.poll().value);
            }
            output.add(levelNodes);
        }
        return output;
    }

    public void helper(TreeNode root, Location location, Map<Integer, PriorityQueue<Location>> levelToNodesMap) {
        if(root == null) {
            return;
        }
        if(!levelToNodesMap.containsKey(location.x)){
            PriorityQueue<Location> pq = new PriorityQueue<>(1, new Comparator<Location>() {
                @Override
                public int compare(Location o1, Location o2) {
                    if(o1.y == o2.y){
                        // if they have same y value sort ascending on values
                        return o1.value - o2.value;
                    }
                    // else sort on descending y
                    return o2.y - o1.y;
                }
            });
            levelToNodesMap.put(location.x, pq);
        }
        location.value = root.val;
        levelToNodesMap.get(location.x).offer(location);
        minLevel = Math.min(location.x, minLevel);
        maxLevel = Math.max(location.x, maxLevel);
        helper(root.left, new Location(location.x-1, location.y-1, Integer.MIN_VALUE), levelToNodesMap);
        helper(root.right, new Location(location.x+1, location.y-1, Integer.MIN_VALUE), levelToNodesMap);
    }
}

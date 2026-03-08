package microsoft_preparation.n_array_tree_level_order_traversal;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        if(root==null){
            return levelOrder;
        }
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0;i<size;i++){
                Node node = queue.poll();
                level.add(node.val);
                queue.addAll(node.children);
            }
            levelOrder.add(level);
        }
        return levelOrder;
    }
}

package microsoft_preparation.binary_tree_next_pointer;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

        public static void main(String[] args) {
            char ch = 'a' + 1;
            System.out.println(ch);
        }

        public Node connect(Node root) {
            helper(root);
            return root;
        }

    private void helper(Node root) {
        if(root==null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            int level = queue.size();
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
            Node node1 = node;
            for(int i=0;i<level;i++){
                Node next = queue.poll();
                node1.next = next;
                node1 = node1.next;
            }
        }
    }
}

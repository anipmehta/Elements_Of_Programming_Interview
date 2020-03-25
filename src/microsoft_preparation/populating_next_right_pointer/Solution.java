package microsoft_preparation.populating_next_right_pointer;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public Node connect(Node root) {
        return helper(root);

    }

    private Node helper(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if(root==null){
            return root;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node tail = null;
            for(int i=0;i<size;i++){
                Node node = queue.poll();
                if (tail==null) {
                    tail = node;
                }
                else {
                    tail.next = node;
                    tail = node;
                }
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    public Node helperOptimized(Node root) {
        if(root==null){
            return root;
        }
        Node leftmost = root;
        while (leftmost != null) {
            Node parent = leftmost;
            Node tail = null;
            while(parent!=null) {
                if(parent.left==null) {
                    break;
                }
                tail = connectChildren(parent, tail);
                parent = parent.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }

    private Node connectChildren(Node parent, Node tail) {
        if(tail != null) {
            tail.next = parent.left;
        }
        parent.left.next = parent.right;
        tail = parent.right;
        return tail;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

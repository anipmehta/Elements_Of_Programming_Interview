package microsoft_preparation.level_order_traversal;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public static void main(String [] args) {
        char ch = 'a' + 1;
        System.out.println(ch);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        return levelOrderIterative(root);
    }

    public List<List<Integer>> levelOrderIterative(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> traversal = new ArrayList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            traversal.add(level);
        }
    return traversal;
    }

}

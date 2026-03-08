package count_good_nodes_in_tree;

import util.TreeNode;

public class Solution {
    public int goodNodes(TreeNode root) {
        return helper(root, Integer.MIN_VALUE);
    }

    public int helper(TreeNode root, int max){
        int count = 0;
        if(root==null){
            return count;
        }

        if(root.val >= max){
            max = root.val;
            count++;
        }
        return count + helper(root.left, max) + helper(root.right, max);
    }
}
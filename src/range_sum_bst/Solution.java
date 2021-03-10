package range_sum_bst;

import util.TreeNode;

public class Solution {
    public static void main(String [] args){
        TreeNode root =  new TreeNode(10);
        root.left = new TreeNode(5);
        root.right =  new TreeNode(3);
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        return helper(root, low, high);
    }

    public int helper(TreeNode root, int low, int high){
        if(root==null){
            return 0;
        }
        if(root.val < low){
            return helper(root.right, low, high);
        }
        if(root.val > high){
            return helper(root.left, low, high);
        }
        return root.val + helper(root.left, low, high) + helper(root.right, low, high);
    }
}

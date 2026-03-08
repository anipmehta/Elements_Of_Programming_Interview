package binary_tree_camera;

import util.TreeNode;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public int minCameraCover(TreeNode root) {
        return Math.min(helper(root, false, true), helper(root, true, true));
    }

    public int helper(TreeNode root, boolean covered, boolean required){
        if(root==null){
            return 0;
        }
        if(required){
            return 1 + helper(root.left, true, false) + helper(root.right, true, false);
        }
        if(covered){
            return Math.min(helper(root.left, false, true) + helper(root.right, false, true),
                    1 + helper(root.left, true, false) + helper(root.right, true, false));
        }
        return helper(root.left, false, true) + helper(root.right, false, true);
    }

}

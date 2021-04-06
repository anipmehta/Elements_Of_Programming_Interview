package min_depth_of_binary_tree;

import util.TreeNode;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    int minDepth = Integer.MAX_VALUE;

    public int minDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        dfs(root, 1);
        return minDepth;
    }

    private void dfs(TreeNode root, int depth) {
        if(root==null){
            return;
        }
        if(root.left==null && root.right == null){
            minDepth = Math.min(minDepth, depth);
        }
        dfs(root.left, depth+1);
        dfs(root.right, depth+1);
    }
}

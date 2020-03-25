package microsoft_preparation.lca_of_binary_tree;

import util.TreeNode;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    private TreeNode lca = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return lca;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return false;
        }
        int left = dfs(root.left, p, q) ? 1 : 0;
        int right = dfs(root.right, p, q) ? 1 : 0;
        int mid = root == p || root == q ? 1 : 0;
        if(left+right+mid>=2){
            lca = root;
        }
        return (left + right + mid) > 0;
    }
}

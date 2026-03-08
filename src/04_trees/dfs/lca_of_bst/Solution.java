package microsoft_preparation.lca_of_bst;

import util.TreeNode;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return iterative(root, p, q);
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }
        if(p.val < root.val && q.val< root.val){
            return helper(root.left, p, q);
        }
        if(p.val > root.val && q.val > root.val){
            return helper(root.right, p, q);
        }
        return root;
    }

    private TreeNode iterative(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node!=null){
            if(node.val > p.val && node.val > q.val){
                node = node.left;
            }
            else if(node.val < p.val && node.val < q.val) {
                node = node.right;
            }
            else{
                return node;
            }
        }
        return null;
    }
}

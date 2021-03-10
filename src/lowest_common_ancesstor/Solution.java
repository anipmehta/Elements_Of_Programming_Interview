package lowest_common_ancesstor;

import util.TreeNode;

public class Solution {
    public static void main(String [] args){
        Solution solution = new Solution();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        root.right.left = new TreeNode(6);
        System.out.println(solution.lowestCommonAncestor(root, root.right.right, root.right.left).val);
    }
    /*
             5
         1       8
       2   3  6     9

       p: 1
       q: 3
       foundP
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }
        return helper(root, p, q);
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        int left = subTree(root.left, p, q);
        int right = subTree(root.right, p, q);
        if(left==2){
            return helper(root.left, p, q);
        }
        if(right==2){
            return helper(root.right, p, q);
        }
        return root;
    }

    private int subTree(TreeNode root, TreeNode p, TreeNode q){
        if(root == null){
            return 0;
        }
        if(root == p || root == q){
            return 1 + subTree(root.left, p, q) + subTree(root.right, p, q);
        }
        return subTree(root.left, p, q) + subTree(root.right, p, q);
    }

}

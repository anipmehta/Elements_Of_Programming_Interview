package tree.binary_tree.diameter_of_a_tree;

import util.TreeNode;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null) {
            return 0;
        }
        return 1 + diameterOfBinaryTree(root.left) +  diameterOfBinaryTree(root.right);
    }
}

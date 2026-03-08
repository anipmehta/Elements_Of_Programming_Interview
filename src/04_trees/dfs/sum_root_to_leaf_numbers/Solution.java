package facebook_practice.sum_root_to_leaf_numbers;

import util.TreeNode;

public class Solution {
    int sum = 0;
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public int sumNumbers(TreeNode root) {
        helper(root, 0);
        return sum;
    }

    private void helper(TreeNode root, int number) {
        if(root==null){
            return;
        }
        int newNumber = number*10 + root.val;
        if(root.left == null && root.right == null){
            sum += newNumber;
        }
        helper(root.left, newNumber);
        helper(root.right, newNumber);
    }
}

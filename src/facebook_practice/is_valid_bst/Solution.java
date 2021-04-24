package facebook_practice.is_valid_bst;

import util.TreeNode;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    private boolean helper(TreeNode root, Integer minValue, Integer maxValue) {
        if(root==null){
            return true;
        }
        if((maxValue!= null && root.val >= maxValue) || (minValue!= null && root.val <=minValue)){
            return false;
        }
        return helper(root.left, minValue, root.val) &&
                helper(root.right, root.val, maxValue);
    }
}

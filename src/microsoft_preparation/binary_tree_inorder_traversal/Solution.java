package microsoft_preparation.binary_tree_inorder_traversal;

import util.TreeNode;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    /*
            1
        2      3
             4   5
     O/P = 2->1->4->3->5
     Stack = 5
     top = 5
     Traversal = 2, 1 , 4, 3, 5
     visited = 1, 2, 3, 4
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> traversal = new ArrayList<>();
        if(root == null){
            return traversal;
        }
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            traversal.add(curr.val);
            curr = curr.right;
        }
        return traversal;
    }
}

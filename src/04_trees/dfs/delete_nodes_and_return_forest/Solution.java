package delete_nodes_and_return_forest;

import util.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> roots = new ArrayList<>();
        Set<Integer> delete = new HashSet<>();
        for(int n : to_delete){
            delete.add(n);
        }
        helper(root, delete, roots, true);
        return roots;
    }
    public static TreeNode helper(TreeNode root, Set<Integer> set, List<TreeNode> out, boolean is_root){
        if(root == null){
            return null;
        }
        boolean isToBeDeleted = set.contains(root.val);
        if(is_root && !isToBeDeleted){
            out.add(root);
        }
        root.left = helper(root.left, set, out, isToBeDeleted);
        root.right = helper(root.right, set, out, isToBeDeleted);
        if(isToBeDeleted){
            return null;
        }
        return root;
    }
}

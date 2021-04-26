package facebook_practice.serialize_and_deserialize;

import util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    String NULL = "NULL";
    String DELIM = ";";
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        Solution solution = new Solution();
        String res = solution.serialize(root);
        System.out.println(res);
        TreeNode de = solution.deserialize(res);
        System.out.println(de.val);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null){
            return "";
        }
        StringBuilder sb =  new StringBuilder();
//        inorder(root, sb);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node==null){
                sb.append(NULL + DELIM);
                continue;
            }
            sb.append(node.val + DELIM);
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data==""){
            return null;
        }
        String [] nodes = data.split(DELIM);
        TreeNode root = null;
        int i = 0;
        while (2*i + 2 < nodes.length){
            if(nodes[i].equals(NULL) || nodes[i].isEmpty()){
                continue;
            }
            TreeNode curr = new TreeNode(Integer.valueOf(nodes[i]));
            System.out.println(curr.val);
            if(root==null) {
                root = curr;
            }
            String left = nodes[2*i+1];
            String right = nodes[2*i+2];
            curr.left = left.equals(NULL) ? null : new TreeNode(Integer.valueOf(left));
            curr.right = right.equals(NULL) ? null : new TreeNode(Integer.valueOf(right));
            i++;
            }
        return root;
    }
}

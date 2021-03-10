package average_of_levels_in_binary_tree;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public static void main(String [] args){
        Solution solution = new Solution();
       TreeNode root = new TreeNode(3);
       root.left = new TreeNode(9);
       root.right = new TreeNode(20);
       root.right.left = new TreeNode(15);
       root.right.right = new TreeNode(7);
       System.out.println(solution.averageOfLevels(root));
    }
    /*
                3
              9   20
             6 5  15  7

                []
                node = [20,null,6,5, null]
                sum = 15 + 7
                count = 2
                list = [3, 14.5, 11]
    */
    public List<Double> averageOfLevels(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<Double> levelAverage = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        double sum = 0;
        double count = 0;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node==null){
//                System.out.println(queue);
                if(!queue.isEmpty()){
                    queue.add(null);
                }
                levelAverage.add(sum/count);
                sum = 0;
                count = 0;
                continue;
            }
            sum += node.val;
            count++;
            if(node.left==null && node.right==null)
            {
                continue;
            }
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
            queue.add(null);
        }
        return levelAverage;
    }
}

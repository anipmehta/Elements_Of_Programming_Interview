package microsoft_preparation.zig_zag_level_traversal;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    class Item{
        int level;
        TreeNode node;
        public Item(int level, TreeNode node){
            this.level = level;
            this.node = node;
        }
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<Item> queue = new LinkedList<>();
        List<List<Integer>> traversal = new ArrayList<>();
        if(root==null){
            return new ArrayList<>();
        }
        queue.offer(new Item(0, root));
        while (!queue.isEmpty()){
            Item item = queue.poll();
            int level = item.level;
            TreeNode node = item.node;
            if(traversal.size()<=level){
                traversal.add(new ArrayList<>());
            }
            traversal.get(level).add(node.val);
            if(level%2==0){
                if(node.right!=null){
                    queue.offer(new Item(level+1, node.right));
                }
                if(node.left!=null){
                    queue.offer(new Item(level+1, node.left));
                }
            }
            else{
                if(node.left!=null){
                    queue.offer(new Item(level+1, node.left));
                }
                if(node.right!=null){
                    queue.offer(new Item(level+1, node.right));
                }
            }

        }
        return traversal;
    }
}

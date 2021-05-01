package boundary_of_binary_tree;

import util.TreeNode;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        Solution solution = new Solution();
        List<Integer> preOrder = new ArrayList<>();
//        System.out.println(solution.boundaryOfBinaryTree(root));
        solution.preOrder(root, preOrder, false);
        System.out.println(preOrder);
    }
    class Item{
        int level;
        TreeNode node;
        int axis;
        public Item(int level, TreeNode node, int axis){
            this.level = level;
            this.node = node;
            this.axis = axis;
        }
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null){
            return res;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> leaves = new ArrayList<>();
        Queue<Item> queue = new LinkedList<>();
        Map<Integer, List<Item>> levelMap = new HashMap<>();
        queue.add(new Item(0, root, 0));
        while (!queue.isEmpty()){
            Item item = queue.poll();
            TreeNode node = item.node;
            if(!levelMap.containsKey(item.level)){
                levelMap.put(item.level, new ArrayList<>());
            }
            levelMap.get(item.level).add(item);
            if(node.left==null && node.right==null){
                leaves.add(node.val);
            }
            if(node.left!=null){
                queue.offer(new Item(item.level+1, node.left, item.axis - 1));
            }
            if(node.right!=null){
                queue.offer(new Item(item.level+1, node.right, item.axis + 1));
            }
        }
        extractBoundary(levelMap, left, right);
        res.addAll(left);
        res.addAll(leaves);
        Collections.reverse(right);
        res.addAll(right);
        return res;
    }

    private void extractBoundary(Map<Integer, List<Item>> levelMap, List<Integer> left, List<Integer> right){
        for(int level : levelMap.keySet()){
            List<Item> items = levelMap.get(level);
            Item first = items.get(0);
            Item last = items.get(items.size()-1);
            if(items.size()==1){
                if(first.node.left==null && first.node.right == null){
                    continue;
                }
                if(first.axis <= 0){
                    left.add(first.node.val);
                }
                else {
                    right.add(first.node.val);
                }

                continue;
            }
            if(!(first.node.left ==null && first.node.right == null)){
                left.add(first.node.val);
            }
            if(!(last.node.left ==null && last.node.right == null)){
                right.add(last.node.val);
            }
        }
    }

//    public void preOrder(TreeNode root, List<Integer> traversal){
//        if(root==null){
//            return;
//        }
//        traversal.add(root.val);
//        preOrder(root.left, traversal);
//        preOrder(root.right, traversal);
//    }

    public void preOrder(TreeNode root, List<Integer> traversal, boolean right){
        if(root==null){
            return;
        }
        if(!right){
            traversal.add(root.val);
            preOrder(root.left, traversal, false);
            preOrder(root.right, traversal, true);
        }
        else{
            preOrder(root.left, traversal, false);
            preOrder(root.right, traversal, true);
            traversal.add(root.val);
        }
    }
}

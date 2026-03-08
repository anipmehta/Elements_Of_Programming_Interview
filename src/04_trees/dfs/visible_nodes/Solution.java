package microsoft_preparation.visible_nodes;

public class Solution {
    class Tree {
        public int x;
        public Tree l;
        public Tree r;
    }

    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public int solution(Tree T) {
        // write your code in Java SE 8
        return helper(T, Integer.MIN_VALUE);
    }

    public int helper(Tree root, int max){
        if(root==null){
            return 0;
        }
        if(root.x >= max){
            return 1 + helper(root.l, root.x) + helper(root.r, root.x);
        }
        return helper(root.l, max) + helper(root.r, max);
    }

}

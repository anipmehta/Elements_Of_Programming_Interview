package lca_with_parent;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        if(p==null || q==null){
            return null;
        }
        Set<Node> parents = new HashSet<>();
        Node node = p;
        while (node!=null){
            parents.add(node);
            node = node.parent;
        }
        node = q;
        while (node!=null){
            if(parents.contains(node)){
                return node;
            }
            node = node.parent;
        }
        return null;
    }
}

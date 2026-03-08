package clutter_classify_shortest_edges_in_weighted_graph;

import java.util.List;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

//    public boolean [] classifyEdges(int total_nodes, int end, int [] from, int [] to, int [] weight) {
//        boolean [] classification = new boolean[from.length];
//        Node [] nodes = new Node[total_nodes];
//        for(int i=0;i<from.length;i++){
//            int from =
//        }
//        return classification;
//    }

    class Node{
        public int index;
        public List<Node> neighbours;
    }
}

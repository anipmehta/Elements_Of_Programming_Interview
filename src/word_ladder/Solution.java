package word_ladder;

import java.lang.reflect.Array;
import java.util.*;

public class Solution{
    public static void main(String [] args){
        String beginWord = "hit";
        String  endWord = "cog";
//        String beginWord = "a";
//        String  endWord = "c";
//        String beginWord = "red";
//        String  endWord = "tax";
        String [] arr = {"hot","dot","dog","lot","log","cog"};
//        String [] arr = {"a", "b", "c"};
//        String [] arr = {"ted","tex","red","tax","tad","den","rex","pee"};
        List<String> wordList = new ArrayList<String>(Arrays.asList(arr));
        for(List<String> path : findLadders(beginWord, endWord, wordList)){
                String way = Arrays.toString(path.toArray());
                System.out.println(way);
        }
    }
    public static class BFSNode{
        public String word;
        public List<String> path;
        public BFSNode(String word, List<String> path){
            this.word = word;
            this.path = path;
        }
    }
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList);
        List<List<String>> transformations = new ArrayList<>();
        if(!dictionary.contains(endWord)){
            return transformations;
        }
        Queue<BFSNode> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        BFSNode start = new BFSNode(beginWord, new ArrayList<>());
        start.path.add(beginWord);
        queue.offer(start);
        int min_count = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            BFSNode node = queue.poll();
            if(node.path.size()>min_count){
                break;
            }
            if(node.word.equals(endWord)){
                transformations.add(node.path);
                min_count = node.path.size();
                continue;
            }
            visited.add(node.word);
            for(int i=0;i<node.word.length();i++){
                for(int j=0;j<26;j++){
                    char ch = (char) ('a' + j);
                    String newWord = new String(node.word.substring(0,i)+ ch + node.word.substring(i+1));
                    if(visited.contains(newWord) && !newWord.equals(endWord)){
                        continue;
                    }
                    if(dictionary.contains(newWord)){
                        List<String> path = new ArrayList<>();
                        path.addAll(node.path);
                        path.add(newWord);
                        BFSNode bfsNode = new BFSNode(newWord, path);
                        queue.offer(bfsNode);
                    }
                }
            }
        }
        return transformations;
    }
}

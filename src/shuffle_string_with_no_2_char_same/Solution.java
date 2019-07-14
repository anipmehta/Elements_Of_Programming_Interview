package shuffle_string_with_no_2_char_same;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String [] args){
        System.out.println(shuffle("ABBBC"));
        System.out.println(shuffle("AA"));
        System.out.println(shuffle("ABBA"));

    }
    public static String shuffle(String string){
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder stringBuilder =  new StringBuilder("");
        PriorityQueue<HeapItem> heap = new PriorityQueue<>(string.length(), new Comparator<HeapItem>() {
            @Override
            public int compare(HeapItem o1, HeapItem o2) {
                return o2.count - o1.count;
            }
        });
        for(Character ch : string.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for(char ch : map.keySet()){
            heap.offer(new HeapItem(ch, map.get(ch)));
        }
        if(heap.isEmpty()){
            return null;
        }
        HeapItem previous=null;
        while(!heap.isEmpty()){
            HeapItem top = heap.poll();
            stringBuilder.append(top.key);
            if(previous!=null && previous.count!=0){
                heap.offer(previous);
            }
            previous = top;
            previous.count -= 1;
        }
        if(previous.count!=0){
            return null;
        }
        return stringBuilder.toString();
    }
    static class HeapItem{
        char key;
        int count;
        public HeapItem(char key, int count){
            this.key = key;
            this.count = count;
        }
    }
}

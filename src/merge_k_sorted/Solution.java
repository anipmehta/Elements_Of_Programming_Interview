package merge_k_sorted;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        Solution solution = new Solution();
        List<List<Integer>> sortedArrays = new ArrayList<>();
        Integer [] arr1 = {1,5,10};
        Integer [] arr2 = {2,3, 4,7};
        Integer [] arr3 = {8,10,12};
        sortedArrays.add(Arrays.asList(arr1));
        sortedArrays.add(Arrays.asList(arr2));
        sortedArrays.add(Arrays.asList(arr3));
        List<Integer> sorted = solution.mergeKSorted(sortedArrays, 3);
        System.out.println(sorted.toString());
    }
    public List<Integer> mergeKSorted(List<List<Integer>> sortedArrays, int k){
        PriorityQueue<HeapItem> heap = new PriorityQueue<>(k, new Comparator<HeapItem>() {
            @Override
            public int compare(HeapItem o1, HeapItem o2) {
                return o1.item - o2.item;
            }
        });
        for(int i=0;i<k;i++){
            heap.offer(new HeapItem(i, 0, sortedArrays.get(i).get(0)));
        }
        List<Integer> sorted = new ArrayList<>();
        while(!heap.isEmpty()){
            HeapItem top = heap.poll();
            sorted.add(top.item);
            int nextIndex = top.itemIndex + 1;
            if(nextIndex >= sortedArrays.get(top.arrayIndex).size()){
                continue;
            }
            HeapItem current = new HeapItem(top.arrayIndex, nextIndex, sortedArrays.get(top.arrayIndex).get(nextIndex));
            heap.offer(current);
        }
        return sorted;
    }
    class HeapItem{
        int item;
        int itemIndex;
        int arrayIndex;
        public HeapItem(int arrayIndex, int itemIndex, int item){
            this.arrayIndex = arrayIndex;
            this.itemIndex = itemIndex;
            this.item = item;
        }
    }
}


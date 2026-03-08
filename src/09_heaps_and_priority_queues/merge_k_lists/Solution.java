package facebook_practice.merge_k_lists;

import microsoft_preparation.swap_adjacent_linked_list_nodes.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null;
        ListNode curr = null;
        int k = lists.length;
        Queue<ListNode> pq = new PriorityQueue<>(k, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null){
                pq.offer(lists[i]);
            }
        }
        while (!pq.isEmpty()){
            ListNode node = pq.poll();
            if(head==null){
                head = node;
            }
           else{
                curr.next = node;
            }
            ListNode next = node.next;
            if(next!=null){
                pq.offer(next);
            }
            node.next = null;
            curr = node;
        }
        return head;
    }
}

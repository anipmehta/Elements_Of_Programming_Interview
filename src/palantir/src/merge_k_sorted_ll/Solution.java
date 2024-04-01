package merge_k_sorted_ll;

import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
public class Solution{
    public static void main(String[] args) {
        ListNode ll1 = new ListNode(1);
        ll1.next = new ListNode(4);
        ll1.next.next = new ListNode(5);
        ListNode ll2 = new ListNode(1);
        ll2.next = new ListNode(3);
        ll2.next.next = new ListNode(4);
        ListNode ll3 = new ListNode(2);
        ll3.next = new ListNode(6);
        ListNode [] listNodes = new ListNode[3];
        listNodes[0] = ll1;
        listNodes[1] = ll2;
        listNodes[2] = ll3;
        ListNode merged = Solution.mergeKLists(listNodes);
        while(merged!=null){
            System.out.println(merged.val);
            merged = merged.next;
        }
    }

    public static ListNode mergeKLists(ListNode [] listNodes){
        ListNode merged = new ListNode();
        ListNode head = merged;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((ListNode l1, ListNode l2) -> l1.val - l2.val);
        for(ListNode node : listNodes){
            // in case the list head is null
            if(node==null){continue;}
            minHeap.offer(node);
        }
        while(!minHeap.isEmpty()){
            ListNode min = minHeap.poll();
            merged.next = new ListNode(min.val);
            merged = merged.next;
            if(min.next!=null){
                minHeap.offer(min.next);
            }
        }
        return head.next;
    }
}
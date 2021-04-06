package reorder_list;

import microsoft_preparation.swap_adjacent_linked_list_nodes.ListNode;

import java.util.Stack;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public void reorderList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;
        int count = 0;
        while(curr!=null){
            count++;
            curr = curr.next;
        }
        curr = head;
        int currCount = 0;
        while (curr!=null){
            if(currCount>= count/2){
                stack.push(curr);
            }
            curr = curr.next;
        }
        curr = head;
        while(!stack.isEmpty()){
            ListNode oldNext = curr.next;
            ListNode node = stack.pop();
            curr.next = node;
            node.next = oldNext;
            curr = node.next;
        }
    }
}

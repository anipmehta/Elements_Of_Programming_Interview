package microsoft_preparation.swap_adjacent_linked_list_nodes;

public class Solution {
    public static void main(String [] args){
       //TODO: Add tests
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        Solution solution = new Solution();
        ListNode swappedHead = solution.swapPairs(head);
        while (swappedHead!=null) {
            System.out.print(swappedHead.val + "->");
            swappedHead = swappedHead.next;
        }
        System.out.print("null");
    }
    public ListNode swapPairs(ListNode head) {
        return helper(head);
    }
    public ListNode helper(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode a = head;
        ListNode b = head.next;
        if(b==null) {
            return head;
        }
        ListNode c = b.next;
        b.next = a;
        a.next = helper(c);
        return b;
    }
}

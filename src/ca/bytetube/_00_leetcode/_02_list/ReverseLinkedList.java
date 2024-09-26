package ca.bytetube._00_leetcode._02_list;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * @author dal
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null;

        while (head != null){
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }

        return newHead;
    }

    public ListNode reverseList0(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList0(head.next);
        //4
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}

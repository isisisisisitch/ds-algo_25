package ca.bytetube._00_leetcode._02_list;

/**
 * https://leetcode.com/problems/palindrome-linked-list/description/
 *
 * @author dal
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
//
//        //1.通过快慢指针找到单链表中点，必须都指向head
//        ListNode slow = head;
//        ListNode fast = head;
//
//        while (fast != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//
//        //2.反转右半部分
//        ListNode newHead = reverseList(slow);
//
//        //3.设定2个指针从两端向中间遍历, 在遍历的过程中只要有一次不相等则return false
//        ListNode left = head;
//        ListNode right = newHead;
//
//        while (right != null) {
//            if (left.val != right.val) return false;
//            left = left.next;
//            right = right.next;
//        }
//
//        //4.恢复现场
//        reverseList(newHead);
//
//        return true;
//        if(head == null) return false;
//        ListNode slow = head;
//        ListNode fast = head;
//        while(fast != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//
//        ListNode mid = reverseList(slow);
//
//        ListNode newHead = head;
//        while(mid != null) {
//            if(newHead.val != mid.val) {
//                return false;
//            }
//            newHead = newHead.next;
//            mid = mid.next;
//        }
//
//        return true;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reversed(slow);
        fast = head;
        while (slow != null) {
            if (slow.val != fast.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        reversed(fast); //还原

        return true;
    }

    private ListNode reversed(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    public ListNode reverseList(ListNode head) {
//        ListNode newHead = null;
//
//        while (head != null) {
//            ListNode tmp = head.next;
//            head.next = newHead;
//            newHead = head;
//            head = tmp;
//        }
//
//        return newHead;
        if(head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}

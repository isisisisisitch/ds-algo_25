package ca.bytetube._00_leetcode._02_list;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 *
 * @author dal
 */
public class DeleteNodeInLinkedList {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

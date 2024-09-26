package ca.bytetube._00_leetcode._02_list;

/**
 * @author dal
 */
public class GetFirstInsertionNode {
    public static void main(String[] args) {
// 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6

        System.out.println(getFirstInsertionNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getFirstInsertionNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getFirstInsertionNode(head1, head2).value);
    }

    public static Node getFirstInsertionNode(Node head1, Node head2) {
        Node cycleNode1 = getCycleNode(head1);
        Node cycleNode2 = getCycleNode(head2);
        if (cycleNode1 == null && cycleNode2 == null) return getNoCycleInsertionNode(head1, head2);
        if (cycleNode1 != null && cycleNode2 != null)
            return getCycleInsertionNode(head1, cycleNode1, head2, cycleNode2);
        return null;
    }

    public static Node getCycleInsertionNode(Node head1, Node cycleNode1, Node head2, Node cycleNode2) {
        Node cur1 = null;
        Node cur2 = null;
        if (cycleNode1 == cycleNode2) {//II
            cur1 = head1;
            cur2 = head2;
            int difference = 0;
            while (cur1.next != cycleNode1) {
                difference++;
                cur1 = cur1.next;
            }

            while (cur2.next != cycleNode2) {
                difference--;
                cur2 = cur2.next;
            }

            cur1 = difference > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            difference = Math.abs(difference);

            //让较长的单链表的指针先跑完difference长度，再让2个指针一起跑，相遇的位置就是相交点
            while (difference != 0) {
                difference--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;
        } else {//I or III
            cur1 = cycleNode1.next;
            while (cur1 != cycleNode1) {
                if (cur1 == cycleNode2) {//III
                    return cycleNode1;
                }
                cur1 = cur1.next;
            }
        }


        return null;
    }


    public static Node getNoCycleInsertionNode(Node head1, Node head2) {
        Node cur1 = head1;
        Node cur2 = head2;
        int difference = 0;
        while (cur1.next != null) {
            difference++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            difference--;
            cur2 = cur2.next;
        }

        cur1 = difference > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        difference = Math.abs(difference);

        //让较长的单链表的指针先跑完difference长度，再让2个指针一起跑，相遇的位置就是相交点
        while (difference != 0) {
            difference--;
            cur1 = cur1.next;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }


        return cur1;

    }

    public static Node getCycleNode(Node head) {
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
}

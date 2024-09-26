package ca.bytetube._00_leetcode._03_stack;


import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/
 *
 * @author dal
 */
class MinStack {
    Node head;

    private static class Node {
        int val;
        Node next;
        int min;

        public Node(int val, Node next, int min) {
            this.val = val;
            this.next = next;
            this.min = min;
        }
    }

    public MinStack() {
        head = new Node(0, null, Integer.MAX_VALUE);
    }

    public void push(int val) {
        head = new Node(val, head, Math.min(head.min, val));
    }

    public void pop() {
        head = head.next;

    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

//    private Stack<Integer> data;
//    private Stack<Integer> min;
//
//    public MinStack() {
//        data = new Stack<>();
//        min = new Stack<>();
//
//    }
//
//    public void push(int val) {
//        data.push(val);
//        if (min.isEmpty()) min.push(val);
//        else if (val <= min.peek()) min.push(val);
//    }
//
//    public void pop() {
//        int pop = data.pop();
//        if (pop == min.peek()) min.pop();
//
//    }
//
//    public int top() {
//        return data.peek();
//    }
//
//    public int getMin() {
//        return min.peek();
//    }
}



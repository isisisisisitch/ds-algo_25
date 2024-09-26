package ca.bytetube._00_leetcode._03_stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/implement-stack-using-queues/
 *
 * @author dal
 */
public class MyStack {
    Queue<Integer> data;
    Queue<Integer> help;

    public MyStack() {
        data = new LinkedList<>();
        help = new LinkedList<>();
    }

    public void push(int x) {
        data.offer(x);
    }

    public int pop() {
        while (data.size() > 1) help.offer(data.poll());
        int polled = data.poll();
        swap();
        return polled;
    }

    public int top() {

        while (data.size() > 1) help.offer(data.poll());
        int polled = data.poll();
        help.offer(polled);
        swap();

        return polled;
    }


    public boolean empty() {
        return data.isEmpty() && help.isEmpty();
    }


    private void swap() {
        Queue<Integer> tmp = help;
        help = data;
        data = tmp;
    }
}

package ca.bytetube._05_queue;

import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }

        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
}

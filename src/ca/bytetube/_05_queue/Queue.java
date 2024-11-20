package ca.bytetube._05_queue;

import ca.bytetube._05_queue.doubly.LinkedList;

public class Queue<E> {
    private LinkedList<E> linkedList = new LinkedList<>();

    public int size() {
        return linkedList.size();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }


    public void enQueue(E element) {
        linkedList.add(element);
    }

    public E deQueue() {
        return linkedList.remove(0);
    }

    // Get the top element of the stack
    public E front() {
        return linkedList.get(0);
    }

    public void clear() {
        linkedList.clear();
    }
}

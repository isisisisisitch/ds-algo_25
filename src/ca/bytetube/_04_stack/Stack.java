package ca.bytetube._04_stack;

import ca.bytetube._04_stack.singly.ArrayList;

public class Stack<E> {
    private ArrayList<E> arrayList = new ArrayList<>();

    public int size() {
        return arrayList.size();
    }


    public boolean isEmpty() {
        return arrayList.isEmpty();
    }


    public void push(E element) {
        arrayList.add(element);
    }

    public E pop() {
        return arrayList.remove(arrayList.size - 1);
    }

    // Get the top element of the stack
    public E top() {
        return arrayList.get(arrayList.size - 1);
    }

    public void clear() {
        arrayList.clear();
    }
}

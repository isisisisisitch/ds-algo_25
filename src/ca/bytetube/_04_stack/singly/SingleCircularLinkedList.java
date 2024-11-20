package ca.bytetube._04_stack.singly;

import ca.bytetube._04_stack.AbstractList;
import ca.bytetube._04_stack.List;

public class SingleCircularLinkedList<E> extends AbstractList<E> implements List<E> {
    private Node<E> first;


    // Clear all the elements
    public void clear() {
        size = 0;
        first = null;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) node = node.next;
        return node;
    }

    @Override
    public void add(int index, E element) {
        //normal/tail/head
        rangeCheckForAdd(index);
        //head
        if (index == 0) {
            Node<E> newFirst = new Node<>(element, first);
            first = newFirst;
            Node<E> last = (size == 0) ? newFirst : node(size - 1);
            last.next = newFirst;
        } else {
            //1.normal/tail
            // int i = 10;//assign right--->left
            //point left--->right
            Node<E> prevNode = node(index - 1);

            prevNode.next = new Node<>(element, prevNode.next);
        }

        size++;

    }

    @Override
    public E remove(int index) {
        //normal/tail/head
        //head
        Node<E> removeNode = first;
        if (index == 0) {
            if (size == 1) first = null;
            else {
                Node<E> last = node(size - 1);
                Node<E> newFirst = first.next;
                first = newFirst;
                last.next = newFirst;
            }

        } else {//normal/tail
            Node<E> prevNode = node(index - 1);
            removeNode = prevNode.next;
            prevNode.next = removeNode.next;
        }
        size--;


        return removeNode.val;
    }


    @Override
    public E get(int index) {
        rangeCheck(index);
        return node(index).val;
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        Node<E> oldNode = node(index);
        E oldVal = oldNode.val;
        oldNode.val = element;

        return oldVal;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.val == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.val)) return i;
                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }


    private static class Node<E> {
        E val;
        Node<E> next;


        public Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }
    }


}

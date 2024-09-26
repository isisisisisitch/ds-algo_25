package ca.bytetube._05_queue.doubly;

import ca.bytetube._05_queue.AbstractList;
import ca.bytetube._05_queue.List;

public class LinkedList<E> extends AbstractList<E> implements List<E> {
    private Node<E> first;

    private Node<E> last;


    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;

    }

    private Node<E> node(int index) {
        rangeCheck(index);
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) node = node.next;
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) node = node.prev;
            return node;
        }


    }

    @Override
    public void add(int index, E element) {
        //normal/tail/head/first node

        rangeCheckForAdd(index);

        if (index == size) {//tail
            Node<E> oldLast = last;
            Node<E> newLast = new Node<>(element, oldLast, null);
            last = newLast;
            //first node
            if (size == 0) first = newLast;

            else oldLast.next = newLast;


        } else {
            //normal/head
            Node<E> nextNode = node(index);
            Node<E> prevNode = nextNode.prev;
            Node<E> newNode = new Node<>(element, prevNode, nextNode);
            nextNode.prev = newNode;
            if (index == 0) first = newNode;
            else prevNode.next = newNode;
        }


        size++;


    }

    @Override
    public E get(int index) {
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
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;
        if (index == 0) first = nextNode;//head
        else prevNode.next = nextNode;
        if (index == size - 1) last = prevNode;//tail
        else nextNode.prev = prevNode;

        size--;
        return node.val;
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
        Node<E> prev;
        Node<E> next;

        public Node(E val, Node<E> prev, Node<E> next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }
}

package ca.bytetube._08_heap;

import ca.bytetube._08_heap.printer.BinaryTreeInfo;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator, E[] elements) {
        super(comparator);
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                this.elements[i] = elements[i];
            }

        }

        heapify();

    }

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];

    }


    public BinaryHeap(E[] elements) {
        this(null, elements);

    }

    public BinaryHeap() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap(int initialCapacity) {
        initialCapacity = initialCapacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : initialCapacity;
        this.elements = (E[]) new Object[initialCapacity];
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);

        elements[size++] = element;
        siftUp(size - 1);

    }

    //1. Cover the root node with the last node
    //2. Delete the last node
    @Override
    public E remove() {
        E root = elements[0];
        int lastIndex = --size;
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);

        return root;
    }

    @Override
    public E get() {
        emptyCheck();

        return elements[0];
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        if (size == 0) {

            root = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);

        }
        return root;
    }

    //3. Loop the following（43 is referred to as node for short）
    // If node < the maximum child node
    // Swap with the maximum child node
    // If node ≥ the maximum child node， or node has not child node
    // Exit the loop
    private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        while (index < half) {
            //对于child node有2种情况
            //1.只有左
            //2.有左有右
            //3.默认与左孩子比较
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            int rightIndex = childIndex + 1;

            //从左右孩子中找到较大值
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }
            //拿较大值和parent节点的值进行比较
            if (compare(element, child) >= 0) break;
            elements[index] = child;
            index = childIndex;

        }

        elements[index] = element;

    }

    private void siftUp(int index) {
        E e = elements[index];

        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            //If node ≤ parent node，or node has not parent node
            // Exit the loop
            if (compare(e, parent) <= 0) break;


            //If node ＞ parent node

            elements[index] = parent;
            index = parentIndex;
        }

        elements[index] = e;

    }

    private void heapify() {
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }


    private void emptyCheck() {
        if (elements.length == 0 || elements == null) throw new RuntimeException("heap is empty! ");
    }

    private void elementNotNullCheck(E element) {
        if (element == null) throw new IllegalArgumentException("element can not be null !");
    }


    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        //扩容
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) newElements[i] = elements[i];

        elements = newElements;

    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int) node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int) node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {

        return elements[(int) node];
    }
}

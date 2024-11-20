package ca.bytetube._02_dynamicarray;


public class ArrayList<E> {
    private int size;
    private E[] elements;

    private static final int ELEMENT_NOT_FOUND = -1;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }


    // Number of elements
    int size() {
        return size;
    }


    // Is it empty
    boolean isEmpty() {
        return size == 0;
    }


    // Clear all the elements
    void clear() {
//        for (int i = 0; i < size; i++) {
//            elements[i] = null;
//        }
        size = 0;
    }


    // Add elements to the end
    void add(E element) {
        add(size, element);
    }

    // Add elements to the index position
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        size++;
    }


    // Returns the element corresponding to the index position
    E get(int index) {
        rangeCheck(index);
        return elements[index];
    }


    // Set the element at the index position
    E set(int index, E element) {
        rangeCheck(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }


    // Delete elements to the index position
    E remove(int index) {
        rangeCheck(index);
        E oldElement = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        size--;

        return oldElement;
    }


    // Return the index of the element
    int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    //Contains a certain element
    boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index: " + index + ",size: " + size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index: " + index + ",size: " + size);
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;

        if (oldCapacity >= capacity) return;

        //代码能够来到这，那么数组容量一定是不够了，需要手动扩容,扩大1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) newElements[i] = elements[i];

        elements = newElements;

    }

}
